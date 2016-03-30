package com.zhuani21.blog.auto.bean;

import java.util.ArrayList;
import java.util.List;

public class JobExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JobExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andJobIdIsNull() {
            addCriterion("job_id is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("job_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(Integer value) {
            addCriterion("job_id =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(Integer value) {
            addCriterion("job_id <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(Integer value) {
            addCriterion("job_id >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("job_id >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(Integer value) {
            addCriterion("job_id <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(Integer value) {
            addCriterion("job_id <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<Integer> values) {
            addCriterion("job_id in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<Integer> values) {
            addCriterion("job_id not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(Integer value1, Integer value2) {
            addCriterion("job_id between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(Integer value1, Integer value2) {
            addCriterion("job_id not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNull() {
            addCriterion("job_name is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("job_name is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("job_name =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("job_name <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("job_name >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("job_name >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("job_name <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("job_name <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("job_name like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("job_name not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("job_name in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("job_name not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("job_name between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("job_name not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeIsNull() {
            addCriterion("job_cycle_type is null");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeIsNotNull() {
            addCriterion("job_cycle_type is not null");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeEqualTo(String value) {
            addCriterion("job_cycle_type =", value, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeNotEqualTo(String value) {
            addCriterion("job_cycle_type <>", value, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeGreaterThan(String value) {
            addCriterion("job_cycle_type >", value, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("job_cycle_type >=", value, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeLessThan(String value) {
            addCriterion("job_cycle_type <", value, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeLessThanOrEqualTo(String value) {
            addCriterion("job_cycle_type <=", value, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeLike(String value) {
            addCriterion("job_cycle_type like", value, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeNotLike(String value) {
            addCriterion("job_cycle_type not like", value, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeIn(List<String> values) {
            addCriterion("job_cycle_type in", values, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeNotIn(List<String> values) {
            addCriterion("job_cycle_type not in", values, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeBetween(String value1, String value2) {
            addCriterion("job_cycle_type between", value1, value2, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobCycleTypeNotBetween(String value1, String value2) {
            addCriterion("job_cycle_type not between", value1, value2, "jobCycleType");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionIsNull() {
            addCriterion("job_description is null");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionIsNotNull() {
            addCriterion("job_description is not null");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionEqualTo(String value) {
            addCriterion("job_description =", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionNotEqualTo(String value) {
            addCriterion("job_description <>", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionGreaterThan(String value) {
            addCriterion("job_description >", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("job_description >=", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionLessThan(String value) {
            addCriterion("job_description <", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionLessThanOrEqualTo(String value) {
            addCriterion("job_description <=", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionLike(String value) {
            addCriterion("job_description like", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionNotLike(String value) {
            addCriterion("job_description not like", value, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionIn(List<String> values) {
            addCriterion("job_description in", values, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionNotIn(List<String> values) {
            addCriterion("job_description not in", values, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionBetween(String value1, String value2) {
            addCriterion("job_description between", value1, value2, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobDescriptionNotBetween(String value1, String value2) {
            addCriterion("job_description not between", value1, value2, "jobDescription");
            return (Criteria) this;
        }

        public Criteria andJobLinkIsNull() {
            addCriterion("job_link is null");
            return (Criteria) this;
        }

        public Criteria andJobLinkIsNotNull() {
            addCriterion("job_link is not null");
            return (Criteria) this;
        }

        public Criteria andJobLinkEqualTo(String value) {
            addCriterion("job_link =", value, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkNotEqualTo(String value) {
            addCriterion("job_link <>", value, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkGreaterThan(String value) {
            addCriterion("job_link >", value, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkGreaterThanOrEqualTo(String value) {
            addCriterion("job_link >=", value, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkLessThan(String value) {
            addCriterion("job_link <", value, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkLessThanOrEqualTo(String value) {
            addCriterion("job_link <=", value, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkLike(String value) {
            addCriterion("job_link like", value, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkNotLike(String value) {
            addCriterion("job_link not like", value, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkIn(List<String> values) {
            addCriterion("job_link in", values, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkNotIn(List<String> values) {
            addCriterion("job_link not in", values, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkBetween(String value1, String value2) {
            addCriterion("job_link between", value1, value2, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobLinkNotBetween(String value1, String value2) {
            addCriterion("job_link not between", value1, value2, "jobLink");
            return (Criteria) this;
        }

        public Criteria andJobStatusIsNull() {
            addCriterion("job_status is null");
            return (Criteria) this;
        }

        public Criteria andJobStatusIsNotNull() {
            addCriterion("job_status is not null");
            return (Criteria) this;
        }

        public Criteria andJobStatusEqualTo(String value) {
            addCriterion("job_status =", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotEqualTo(String value) {
            addCriterion("job_status <>", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusGreaterThan(String value) {
            addCriterion("job_status >", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusGreaterThanOrEqualTo(String value) {
            addCriterion("job_status >=", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLessThan(String value) {
            addCriterion("job_status <", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLessThanOrEqualTo(String value) {
            addCriterion("job_status <=", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLike(String value) {
            addCriterion("job_status like", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotLike(String value) {
            addCriterion("job_status not like", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusIn(List<String> values) {
            addCriterion("job_status in", values, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotIn(List<String> values) {
            addCriterion("job_status not in", values, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusBetween(String value1, String value2) {
            addCriterion("job_status between", value1, value2, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotBetween(String value1, String value2) {
            addCriterion("job_status not between", value1, value2, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andOldFilenameIsNull() {
            addCriterion("old_filename is null");
            return (Criteria) this;
        }

        public Criteria andOldFilenameIsNotNull() {
            addCriterion("old_filename is not null");
            return (Criteria) this;
        }

        public Criteria andOldFilenameEqualTo(String value) {
            addCriterion("old_filename =", value, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameNotEqualTo(String value) {
            addCriterion("old_filename <>", value, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameGreaterThan(String value) {
            addCriterion("old_filename >", value, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameGreaterThanOrEqualTo(String value) {
            addCriterion("old_filename >=", value, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameLessThan(String value) {
            addCriterion("old_filename <", value, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameLessThanOrEqualTo(String value) {
            addCriterion("old_filename <=", value, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameLike(String value) {
            addCriterion("old_filename like", value, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameNotLike(String value) {
            addCriterion("old_filename not like", value, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameIn(List<String> values) {
            addCriterion("old_filename in", values, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameNotIn(List<String> values) {
            addCriterion("old_filename not in", values, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameBetween(String value1, String value2) {
            addCriterion("old_filename between", value1, value2, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andOldFilenameNotBetween(String value1, String value2) {
            addCriterion("old_filename not between", value1, value2, "oldFilename");
            return (Criteria) this;
        }

        public Criteria andFilepathIsNull() {
            addCriterion("filepath is null");
            return (Criteria) this;
        }

        public Criteria andFilepathIsNotNull() {
            addCriterion("filepath is not null");
            return (Criteria) this;
        }

        public Criteria andFilepathEqualTo(String value) {
            addCriterion("filepath =", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotEqualTo(String value) {
            addCriterion("filepath <>", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathGreaterThan(String value) {
            addCriterion("filepath >", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathGreaterThanOrEqualTo(String value) {
            addCriterion("filepath >=", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLessThan(String value) {
            addCriterion("filepath <", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLessThanOrEqualTo(String value) {
            addCriterion("filepath <=", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLike(String value) {
            addCriterion("filepath like", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotLike(String value) {
            addCriterion("filepath not like", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathIn(List<String> values) {
            addCriterion("filepath in", values, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotIn(List<String> values) {
            addCriterion("filepath not in", values, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathBetween(String value1, String value2) {
            addCriterion("filepath between", value1, value2, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotBetween(String value1, String value2) {
            addCriterion("filepath not between", value1, value2, "filepath");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}