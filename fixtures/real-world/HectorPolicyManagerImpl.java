package net.retakethe.policyauction.data.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import me.prettyprint.cassandra.utils.TimeUUIDUtils;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.RangeSlicesQuery;
import net.retakethe.policyauction.data.api.PolicyDAO;
import net.retakethe.policyauction.data.api.PolicyID;
import net.retakethe.policyauction.data.api.PolicyManager;
import net.retakethe.policyauction.data.impl.query.VariableValueTypedColumnSlice;
import net.retakethe.policyauction.data.impl.query.VariableValueTypedSliceQuery;
import net.retakethe.policyauction.data.impl.schema.NamedColumn;
import net.retakethe.policyauction.data.impl.schema.Schema;
import net.retakethe.policyauction.util.CollectionUtils;
import net.retakethe.policyauction.util.Functional;
import net.retakethe.policyauction.util.Functional.Filter;
import net.retakethe.policyauction.util.Functional.SkippedElementException;

/**
 * @author Nick Clarke
 */
public class HectorPolicyManagerImpl extends AbstractHectorDAOManager implements PolicyManager {

    private final Keyspace _keyspace;

    public HectorPolicyManagerImpl(Keyspace keyspace) {
        super();
        if (keyspace == null) {
            throw new IllegalArgumentException("keyspace must not be null");
        }
        _keyspace = keyspace;
    }

    @Override
    public PolicyID makePolicyID(String idString) {
        return new HectorPolicyIDImpl(idString);
    }

    @Override
    public PolicyDAO getPolicy(PolicyID policyID) throws NoSuchPolicyException {
        HectorPolicyIDImpl idImpl = getPolicyIDImpl(policyID);
        UUID key = idImpl.getUUID();

        List<NamedColumn<UUID, String, ?>> list = CollectionUtils.list(
                (NamedColumn<UUID, String, ?>) Schema.POLICIES.SHORT_NAME,
                (NamedColumn<UUID, String, ?>) Schema.POLICIES.DESCRIPTION,
                (NamedColumn<UUID, String, ?>) Schema.POLICIES.LAST_EDITED);
        VariableValueTypedSliceQuery<UUID, String> query =
                Schema.POLICIES.createVariableValueTypedSliceQuery(_keyspace, list, key);

        QueryResult<VariableValueTypedColumnSlice<String>> queryResult = query.execute();

        VariableValueTypedColumnSlice<String> cs = queryResult.get();

        String shortName;
        String description;
        Date lastEdited;
        try {
            shortName = getNonNullColumn(cs, Schema.POLICIES.SHORT_NAME);
        } catch (NoSuchColumnException e) {
            throw new NoSuchPolicyException(policyID);
        }
        try {
            description = getNonNullColumn(cs, Schema.POLICIES.DESCRIPTION);
            lastEdited = getNonNullColumn(cs, Schema.POLICIES.LAST_EDITED);
        } catch (NoSuchColumnException e) {
            throw new RuntimeException("Invalid policy record for key " + key, e);
        }

        return new PolicyDAOImpl(idImpl, shortName, description, lastEdited);
    }

    @Override
    public PolicyDAO createPolicy() {
        UUID uuid = TimeUUIDUtils.getUniqueTimeUUIDinMillis();
        HectorPolicyIDImpl policyID = new HectorPolicyIDImpl(uuid);
        return new PolicyDAOImpl(policyID);
    }

    @Override
    public List<PolicyDAO> getAllPolicies() {
        List<NamedColumn<UUID, String, String>> list = CollectionUtils.list(
                Schema.POLICIES.SHORT_NAME,
                Schema.POLICIES.DESCRIPTION);
        RangeSlicesQuery<UUID, String, String> query =
                Schema.POLICIES.createRangeSlicesQuery(_keyspace,
                        list);

        // TODO: may need paging of data once we have more than a few hundred.
        //       This may need some sort of indexing since we're using RandomPartitioner,
        //       in order to return them in a useful order.
        query.setRowCount(1000);
        // TODO: needed?
        // query.setKeys("fake_key_0", "fake_key_4");

        QueryResult<OrderedRows<UUID, String, String>> result = query.execute();

        OrderedRows<UUID, String, String> orderedRows = result.get();
        if (orderedRows == null) {
            return Collections.emptyList();
        }

        return Functional.filter(orderedRows.getList(),
                new Filter<Row<UUID, String, String>, PolicyDAO>() {
                    @Override
                    public PolicyDAO filter(Row<UUID, String, String> row) throws SkippedElementException {
                        ColumnSlice<String, String> cs = row.getColumnSlice();
                        if (cs == null) {
                            throw new SkippedElementException();
                        }

                        String shortName;
                        try {
                            shortName = getNonNullStringColumn(cs, Schema.POLICIES.SHORT_NAME.getName());
                        } catch (NoSuchColumnException e) {
                            // Tombstone row
                            throw new SkippedElementException();
                        }

                        String description = getStringColumnOrNull(cs, Schema.POLICIES.DESCRIPTION.getName());

                        // FIXME: can't get date from string result.
                        //        To fix this, we need variable-value-typed range slices queries.
                        return new PolicyDAOImpl(new HectorPolicyIDImpl(row.getKey()), shortName, description,
                                new Date());
                    }
                });
    }

    @Override
    public void persist(PolicyDAO policy) {
        PolicyDAOImpl impl = getImpl(policy, PolicyDAOImpl.class);
        UUID policyID = impl.getPolicyID().getUUID();

        Mutator<UUID> m = Schema.POLICIES.createMutator(_keyspace);

        Schema.POLICIES.SHORT_NAME.addInsertion(m, policyID, policy.getShortName());
        Schema.POLICIES.DESCRIPTION.addInsertion(m, policyID, policy.getDescription());

        // We're saving changes, so update the edit time
        Schema.POLICIES.LAST_EDITED.addInsertion(m, policyID, new Date());

        // TODO: error handling? Throws HectorException.
        m.execute();
    }

    @Override
    public void deletePolicy(PolicyDAO policy) {
        PolicyDAOImpl impl = getImpl(policy, PolicyDAOImpl.class);
        UUID policyID = impl.getPolicyID().getUUID();

        Mutator<UUID> m = Schema.POLICIES.createMutator(_keyspace);

        Schema.POLICIES.addRowDeletion(m, policyID);

        m.execute();

        // TODO: this will need to delete from other ColumnFamilies too and trigger recalcs
    }
}