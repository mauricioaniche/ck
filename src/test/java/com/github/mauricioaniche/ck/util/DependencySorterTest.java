package com.github.mauricioaniche.ck.util;

import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKMethodResult;
import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import com.github.mauricioaniche.ck.metric.RunAfter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DependencySorterTest {

    private final DependencySorter sorter = new DependencySorter();

    static class CM1 implements ClassLevelMetric {
        @Override
        public void setResult(CKClassResult result) {
        }
    }

    @RunAfter(metrics={CM3.class})
    static class CM2 implements ClassLevelMetric {
        @Override
        public void setResult(CKClassResult result) {
        }
    }

    static class CM3 implements ClassLevelMetric {
        @Override
        public void setResult(CKClassResult result) {
        }
    }

    static class CM4 implements ClassLevelMetric {
        @Override
        public void setResult(CKClassResult result) {
        }
    }

    static class MM1 implements MethodLevelMetric {
        @Override
        public void setResult(CKMethodResult result) {
        }
    }

    @RunAfter(metrics={CM3.class, MM1.class})
    static class CM5 implements ClassLevelMetric, MethodLevelMetric {
        @Override
        public void setResult(CKClassResult result) {
        }

        @Override
        public void setResult(CKMethodResult result) {

        }
    }

    @Test
    void topologicalOrder() {
        List<Class<? extends ClassLevelMetric>> elements = Arrays.asList(CM1.class, CM2.class, CM3.class);
        List<Class<? extends ClassLevelMetric>> resultingList = sorter.sort(elements);

        assertThat(resultingList).containsExactlyInAnyOrder(CM1.class, CM3.class, CM2.class);
    }

    @Test
    void notAProblemIfRunAfterIsNeverDefined() {
        List<Class<? extends ClassLevelMetric>> elements = Arrays.asList(CM1.class, CM3.class, CM4.class);
        List<Class<? extends ClassLevelMetric>> resultingList = sorter.sort(elements);

        assertThat(resultingList).containsExactlyInAnyOrder(CM1.class, CM3.class, CM4.class);
    }

    @Test
    void ignoreMetricsAtDifferentLevel() {
        List<Class<? extends ClassLevelMetric>> elements = Arrays.asList(CM5.class, CM3.class);
        List<Class<? extends ClassLevelMetric>> resultingList = sorter.sort(elements);

        assertThat(resultingList).containsExactlyInAnyOrder(CM3.class, CM5.class);
    }
}
