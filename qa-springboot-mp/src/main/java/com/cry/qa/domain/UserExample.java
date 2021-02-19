package com.cry.qa.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

@ApiModel(value = "com.cry.qa.domain.UserExample", description = "用户表复杂查询封装类")
public class UserExample {

    @ApiModelProperty(value = "排序条件")
    protected String orderByClause;

    @ApiModelProperty(value = "是否唯一")
    protected boolean distinct;

    @ApiModelProperty(value = "查询条件")
    protected List<Criteria> oredCriteria;

    @ApiModelProperty(value = "查询行数限制")
    private Integer limit;

    @ApiModelProperty(value = "起始行数")
    private Integer offset;

    /**
     * UserExample()的无参构造,生成一个List<Criteria>对象作为oredCriteria的属性
     */
    public UserExample() {
        this.oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * @param orderByClause, set方法
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * orderByClause, get方法
     *
     * @return
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * @param distinct, set方法
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * distinct, get方法
     *
     * @return
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * oredCriteria, get方法
     *
     * @return
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * criteria, 往查询条件List<Criteria>中add条件
     *
     * @param criteria
     */
    public void or(Criteria criteria) {
        this.oredCriteria.add(criteria);
    }

    /**
     * 创建一个criteria,往查询条件List<Criteria>中add后,返回该criteria
     *
     * @return
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        this.oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * 如果List<Criteria>为空, 则创建一个criteria添加后, 并返回criteria
     *
     * @return
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (this.oredCriteria.size() == 0) {
            this.oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * 创建一个新的criteria
     *
     * @return
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除所有查询条件: oredCriteria, orderByClause, distinct
     */
    public void clear() {
        this.oredCriteria.clear();
        this.orderByClause = null;
        this.distinct = false;
    }

    /**
     * setLimit
     *
     * @param limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * getLimit
     *
     * @return
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * setOffset
     *
     * @param offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * getOffset
     *
     * @return
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * 一个抽象的静态内部类GeneratedCriteria,定义了通用的查询规则
     */
    protected abstract static class GeneratedCriteria {

        /**
         * 封装了系列查询条件: Criterion => List<Criterion>
         */
        protected List<Criterion> criteria;

        /**
         * 创建一个List<Criterion>的criteria
         */
        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        /**
         * 判断是否是个有效的criteria
         *
         * @return
         */
        public boolean isValid() {
            return criteria.size() > 0;
        }

        /**
         * 返回已有的criteria
         *
         * @return
         */
        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        /**
         * 获得一个criteria
         *
         * @return
         */
        public List<Criterion> getCriteria() {
            return criteria;
        }

        /**
         * 往criteria增加一个Criterion(condition)条件, condition不能为空
         *
         * @param condition
         */
        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        /**
         * 往criteria增加一个Criterion(condition,value,property)条件, condition,value不能为空
         *
         * @param condition
         * @param value
         * @param property
         */
        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        /**
         * 往criteria增加一个Criterion(condition,value1,value2,property)条件, condition,value不能为空
         * @param condition
         * @param value1
         * @param value2
         * @param property
         */
        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("pwd is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("pwd is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("pwd =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("pwd <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("pwd >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("pwd >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("pwd <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("pwd <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("pwd like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("pwd not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("pwd in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("pwd not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("pwd between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("pwd not between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andRmbIsNull() {
            addCriterion("rmb is null");
            return (Criteria) this;
        }

        public Criteria andRmbIsNotNull() {
            addCriterion("rmb is not null");
            return (Criteria) this;
        }

        public Criteria andRmbEqualTo(Integer value) {
            addCriterion("rmb =", value, "rmb");
            return (Criteria) this;
        }

        public Criteria andRmbNotEqualTo(Integer value) {
            addCriterion("rmb <>", value, "rmb");
            return (Criteria) this;
        }

        public Criteria andRmbGreaterThan(Integer value) {
            addCriterion("rmb >", value, "rmb");
            return (Criteria) this;
        }

        public Criteria andRmbGreaterThanOrEqualTo(Integer value) {
            addCriterion("rmb >=", value, "rmb");
            return (Criteria) this;
        }

        public Criteria andRmbLessThan(Integer value) {
            addCriterion("rmb <", value, "rmb");
            return (Criteria) this;
        }

        public Criteria andRmbLessThanOrEqualTo(Integer value) {
            addCriterion("rmb <=", value, "rmb");
            return (Criteria) this;
        }

        public Criteria andRmbIn(List<Integer> values) {
            addCriterion("rmb in", values, "rmb");
            return (Criteria) this;
        }

        public Criteria andRmbNotIn(List<Integer> values) {
            addCriterion("rmb not in", values, "rmb");
            return (Criteria) this;
        }

        public Criteria andRmbBetween(Integer value1, Integer value2) {
            addCriterion("rmb between", value1, value2, "rmb");
            return (Criteria) this;
        }

        public Criteria andRmbNotBetween(Integer value1, Integer value2) {
            addCriterion("rmb not between", value1, value2, "rmb");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Timestamp value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Timestamp value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Timestamp value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Timestamp value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Timestamp> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Timestamp> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIsNull() {
            addCriterion("loginTime is null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIsNotNull() {
            addCriterion("loginTime is not null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeEqualTo(Timestamp value) {
            addCriterion("loginTime =", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotEqualTo(Timestamp value) {
            addCriterion("loginTime <>", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeGreaterThan(Timestamp value) {
            addCriterion("loginTime >", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("loginTime >=", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLessThan(Timestamp value) {
            addCriterion("loginTime <", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("loginTime <=", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIn(List<Timestamp> values) {
            addCriterion("loginTime in", values, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotIn(List<Timestamp> values) {
            addCriterion("loginTime not in", values, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("loginTime between", value1, value2, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("loginTime not between", value1, value2, "loginTime");
            return (Criteria) this;
        }

        public Criteria andWxopenidIsNull() {
            addCriterion("wxopenid is null");
            return (Criteria) this;
        }

        public Criteria andWxopenidIsNotNull() {
            addCriterion("wxopenid is not null");
            return (Criteria) this;
        }

        public Criteria andWxopenidEqualTo(String value) {
            addCriterion("wxopenid =", value, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidNotEqualTo(String value) {
            addCriterion("wxopenid <>", value, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidGreaterThan(String value) {
            addCriterion("wxopenid >", value, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidGreaterThanOrEqualTo(String value) {
            addCriterion("wxopenid >=", value, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidLessThan(String value) {
            addCriterion("wxopenid <", value, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidLessThanOrEqualTo(String value) {
            addCriterion("wxopenid <=", value, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidLike(String value) {
            addCriterion("wxopenid like", value, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidNotLike(String value) {
            addCriterion("wxopenid not like", value, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidIn(List<String> values) {
            addCriterion("wxopenid in", values, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidNotIn(List<String> values) {
            addCriterion("wxopenid not in", values, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidBetween(String value1, String value2) {
            addCriterion("wxopenid between", value1, value2, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andWxopenidNotBetween(String value1, String value2) {
            addCriterion("wxopenid not between", value1, value2, "wxopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidIsNull() {
            addCriterion("qqopenid is null");
            return (Criteria) this;
        }

        public Criteria andQqopenidIsNotNull() {
            addCriterion("qqopenid is not null");
            return (Criteria) this;
        }

        public Criteria andQqopenidEqualTo(String value) {
            addCriterion("qqopenid =", value, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidNotEqualTo(String value) {
            addCriterion("qqopenid <>", value, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidGreaterThan(String value) {
            addCriterion("qqopenid >", value, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidGreaterThanOrEqualTo(String value) {
            addCriterion("qqopenid >=", value, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidLessThan(String value) {
            addCriterion("qqopenid <", value, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidLessThanOrEqualTo(String value) {
            addCriterion("qqopenid <=", value, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidLike(String value) {
            addCriterion("qqopenid like", value, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidNotLike(String value) {
            addCriterion("qqopenid not like", value, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidIn(List<String> values) {
            addCriterion("qqopenid in", values, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidNotIn(List<String> values) {
            addCriterion("qqopenid not in", values, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidBetween(String value1, String value2) {
            addCriterion("qqopenid between", value1, value2, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQqopenidNotBetween(String value1, String value2) {
            addCriterion("qqopenid not between", value1, value2, "qqopenid");
            return (Criteria) this;
        }

        public Criteria andQuestioncntIsNull() {
            addCriterion("questioncnt is null");
            return (Criteria) this;
        }

        public Criteria andQuestioncntIsNotNull() {
            addCriterion("questioncnt is not null");
            return (Criteria) this;
        }

        public Criteria andQuestioncntEqualTo(Integer value) {
            addCriterion("questioncnt =", value, "questioncnt");
            return (Criteria) this;
        }

        public Criteria andQuestioncntNotEqualTo(Integer value) {
            addCriterion("questioncnt <>", value, "questioncnt");
            return (Criteria) this;
        }

        public Criteria andQuestioncntGreaterThan(Integer value) {
            addCriterion("questioncnt >", value, "questioncnt");
            return (Criteria) this;
        }

        public Criteria andQuestioncntGreaterThanOrEqualTo(Integer value) {
            addCriterion("questioncnt >=", value, "questioncnt");
            return (Criteria) this;
        }

        public Criteria andQuestioncntLessThan(Integer value) {
            addCriterion("questioncnt <", value, "questioncnt");
            return (Criteria) this;
        }

        public Criteria andQuestioncntLessThanOrEqualTo(Integer value) {
            addCriterion("questioncnt <=", value, "questioncnt");
            return (Criteria) this;
        }

        public Criteria andQuestioncntIn(List<Integer> values) {
            addCriterion("questioncnt in", values, "questioncnt");
            return (Criteria) this;
        }

        public Criteria andQuestioncntNotIn(List<Integer> values) {
            addCriterion("questioncnt not in", values, "questioncnt");
            return (Criteria) this;
        }

        public Criteria andQuestioncntBetween(Integer value1, Integer value2) {
            addCriterion("questioncnt between", value1, value2, "questioncnt");
            return (Criteria) this;
        }

        public Criteria andQuestioncntNotBetween(Integer value1, Integer value2) {
            addCriterion("questioncnt not between", value1, value2, "questioncnt");
            return (Criteria) this;
        }

        public Criteria andAnswercntIsNull() {
            addCriterion("answercnt is null");
            return (Criteria) this;
        }

        public Criteria andAnswercntIsNotNull() {
            addCriterion("answercnt is not null");
            return (Criteria) this;
        }

        public Criteria andAnswercntEqualTo(Integer value) {
            addCriterion("answercnt =", value, "answercnt");
            return (Criteria) this;
        }

        public Criteria andAnswercntNotEqualTo(Integer value) {
            addCriterion("answercnt <>", value, "answercnt");
            return (Criteria) this;
        }

        public Criteria andAnswercntGreaterThan(Integer value) {
            addCriterion("answercnt >", value, "answercnt");
            return (Criteria) this;
        }

        public Criteria andAnswercntGreaterThanOrEqualTo(Integer value) {
            addCriterion("answercnt >=", value, "answercnt");
            return (Criteria) this;
        }

        public Criteria andAnswercntLessThan(Integer value) {
            addCriterion("answercnt <", value, "answercnt");
            return (Criteria) this;
        }

        public Criteria andAnswercntLessThanOrEqualTo(Integer value) {
            addCriterion("answercnt <=", value, "answercnt");
            return (Criteria) this;
        }

        public Criteria andAnswercntIn(List<Integer> values) {
            addCriterion("answercnt in", values, "answercnt");
            return (Criteria) this;
        }

        public Criteria andAnswercntNotIn(List<Integer> values) {
            addCriterion("answercnt not in", values, "answercnt");
            return (Criteria) this;
        }

        public Criteria andAnswercntBetween(Integer value1, Integer value2) {
            addCriterion("answercnt between", value1, value2, "answercnt");
            return (Criteria) this;
        }

        public Criteria andAnswercntNotBetween(Integer value1, Integer value2) {
            addCriterion("answercnt not between", value1, value2, "answercnt");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andAuthIsNull() {
            addCriterion("auth is null");
            return (Criteria) this;
        }

        public Criteria andAuthIsNotNull() {
            addCriterion("auth is not null");
            return (Criteria) this;
        }

        public Criteria andAuthEqualTo(Integer value) {
            addCriterion("auth =", value, "auth");
            return (Criteria) this;
        }

        public Criteria andAuthNotEqualTo(Integer value) {
            addCriterion("auth <>", value, "auth");
            return (Criteria) this;
        }

        public Criteria andAuthGreaterThan(Integer value) {
            addCriterion("auth >", value, "auth");
            return (Criteria) this;
        }

        public Criteria andAuthGreaterThanOrEqualTo(Integer value) {
            addCriterion("auth >=", value, "auth");
            return (Criteria) this;
        }

        public Criteria andAuthLessThan(Integer value) {
            addCriterion("auth <", value, "auth");
            return (Criteria) this;
        }

        public Criteria andAuthLessThanOrEqualTo(Integer value) {
            addCriterion("auth <=", value, "auth");
            return (Criteria) this;
        }

        public Criteria andAuthIn(List<Integer> values) {
            addCriterion("auth in", values, "auth");
            return (Criteria) this;
        }

        public Criteria andAuthNotIn(List<Integer> values) {
            addCriterion("auth not in", values, "auth");
            return (Criteria) this;
        }

        public Criteria andAuthBetween(Integer value1, Integer value2) {
            addCriterion("auth between", value1, value2, "auth");
            return (Criteria) this;
        }

        public Criteria andAuthNotBetween(Integer value1, Integer value2) {
            addCriterion("auth not between", value1, value2, "auth");
            return (Criteria) this;
        }

        public Criteria andExperienceIsNull() {
            addCriterion("experience is null");
            return (Criteria) this;
        }

        public Criteria andExperienceIsNotNull() {
            addCriterion("experience is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceEqualTo(Integer value) {
            addCriterion("experience =", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotEqualTo(Integer value) {
            addCriterion("experience <>", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceGreaterThan(Integer value) {
            addCriterion("experience >", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceGreaterThanOrEqualTo(Integer value) {
            addCriterion("experience >=", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLessThan(Integer value) {
            addCriterion("experience <", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLessThanOrEqualTo(Integer value) {
            addCriterion("experience <=", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceIn(List<Integer> values) {
            addCriterion("experience in", values, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotIn(List<Integer> values) {
            addCriterion("experience not in", values, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceBetween(Integer value1, Integer value2) {
            addCriterion("experience between", value1, value2, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotBetween(Integer value1, Integer value2) {
            addCriterion("experience not between", value1, value2, "experience");
            return (Criteria) this;
        }
    }

    /**
     * Criteria, 调用了父类的构造方法: criteria = new ArrayList<Criterion>();
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 定义了一个静态内部类Criterion,封装了具体的查询条件字段值
     */
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