package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//Calculates the tight and loose class cohesion for a class.
//For more details see: https://www.aivosto.com/project/help/pm-oo-cohesion.html#TCC_LCC
@RunAfter(metrics={RFC.class, MethodLevelFieldUsageCount.class, MethodInvocationsLocal.class})
public class TightClassCohesion implements CKASTVisitor, ClassLevelMetric {
    private HashMap<String, Set<String>> accessedFields = new HashMap<>();

    //Two methods are directly connected if:
    //1. both access the same class-level variable
    //2. their call trees access the same class-level variable (only within the class)
    private Set<ImmutablePair<String, String>> getDirectConnections(CKClassResult result){
        for (CKMethodResult method : result.getMethods()){
            accessedFields.put(method.getMethodName(), method.getFieldsAccessed());
        }

        HashMap<String, Set<String>> allAccessedFields = new HashMap<>();
        for (CKMethodResult method : result.getVisibleMethods()){
            Set<String> allLocalFields = collectAccessedFields(method);
            allLocalFields.addAll(method.getFieldsAccessed());
            allAccessedFields.put(method.getMethodName(), allLocalFields);
        }

        Set<ImmutablePair<String, String>> directConnections = new HashSet<>();
        for(String firstKey : allAccessedFields.keySet()){
            for(String secondKey : allAccessedFields.keySet()){
                Set<String> accessedFieldsFirst = Sets.newHashSet(allAccessedFields.get(firstKey));
                Set<String> accessedFieldsSecond = allAccessedFields.get(secondKey);
                accessedFieldsFirst.retainAll(accessedFieldsSecond);
                if(!firstKey.equals(secondKey) && accessedFieldsFirst.size() > 0){
                    directConnections.add(new ImmutablePair<String, String>(firstKey, secondKey));
                }
            }
        }
        return directConnections;
    }

    //Collect all accessed fields from the invocation tree of a method
    private Set<String> collectAccessedFields(CKMethodResult method){
        Set<String> allLocalInvocations = method.getMethodInvocationsIndirectLocal().keySet();

        Set<String> allLocalFields = new HashSet<>();
        for (String invocation : allLocalInvocations){
            Set<String> currentFields = accessedFields.get(invocation);
            if(currentFields != null)
                allLocalFields.addAll(currentFields);
        }

        return allLocalFields;
    }

    //Two methods are indirectly connected if:
    //1. they are not directly connected
    //2. they are connected via other methods, e.g. X -> Y -> Z
    private Set<ImmutablePair<String, String>> getIndirectConnections(CKClassResult result, Set<ImmutablePair<String, String>> directConnections){
        //convert the direct connections into a Map in order to simplify the usage
        HashMap<String, Set<String>> directConnectionsMap = new HashMap<>();
        for(CKMethodResult method : result.getMethods()){
            directConnectionsMap.put(method.getMethodName(), Sets.newHashSet(Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY)));
        }
        for(ImmutablePair<String, String> pair : directConnections){
            directConnectionsMap.get(pair.left).add(pair.right);
        }

        //extract all direct and indirect connections between methods from the direct connections
        HashMap<String, Set<String>> indirectConnectionsMap = new HashMap<>();
        for (CKMethodResult method : result.getVisibleMethods()){
            Set<String> localConnections = extractConnections(method.getMethodName(), new HashSet<>(), directConnectionsMap);
            indirectConnectionsMap.put(method.getMethodName(), localConnections);
        }

        //map the indirect connections into connection pairs
        Set<ImmutablePair<String, String>> indirectConnections = new HashSet<>();
        for(String key : indirectConnectionsMap.keySet()){
            indirectConnections.addAll(indirectConnectionsMap.get(key).stream()
                    .filter(right -> !key.equals(right))
                    .map(right -> new ImmutablePair<String, String>(key, right))
                    .collect(Collectors.toSet()));
        }
        //remove all direct connections
        indirectConnections.removeAll(directConnections);
        return indirectConnections;
    }

    //Recursively extract all indirect connections between methods starting with the given method
    //Explored contains all previously explored connections
    //connections contains all direct method connections of interest
    private Set<String> extractConnections(String currentConnection, Set<String> explored, HashMap<String, Set<String>> connections){
        explored.add(currentConnection);

        //only explore connections that were not previously explored
        Set<String> nextConnections = connections.get(currentConnection).stream()
                .filter(connection -> !explored.contains(connection))
                .collect(Collectors.toSet());
        explored.addAll(nextConnections);
        for (String nextConnection : nextConnections){
            explored.addAll(extractConnections(nextConnection, explored, connections));
        }

        //Stops when all invocations are explored: there are no more invocations to be explored
        return explored;
    }

    public void setResult(CKClassResult result) {
        //in case the class does not contain any visible methods, TCC and LCC have no reasonable value, thus set it to -1
        if(result.getVisibleMethods().size() < 1){
            result.setTightClassCohesion(-1);
            result.setLooseClassCohesion(-1);
        } else {
            //maximum number of possible connections (N * (N -1))
            float np = result.getVisibleMethods().size() * (result.getVisibleMethods().size() - 1);

            //number of direct connections (number of edges in the connection graph) in this class
            Set<ImmutablePair<String, String>> directConnections = getDirectConnections(result);
            result.setTightClassCohesion(directConnections.size() / np);

            //number of indirect connections in this class
            Set<ImmutablePair<String, String>> indirectConnections = getIndirectConnections(result, directConnections);
            result.setLooseClassCohesion((directConnections.size() + indirectConnections.size()) / np);
        }
    }
}