package com.xiaomi.xiaoai.servicefunc.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import cn.org.atool.fluent.mybatis.functions.TableSupplier;
import java.io.Serializable;
import java.util.Date;
import java.util.function.Consumer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * TbResultEntity: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@SuppressWarnings({"unchecked"})
@Data
@Accessors(
    chain = true
)
@EqualsAndHashCode(
    callSuper = false
)
@FluentMybatis(
    table = "tb_result",
    schema = "db_monitor"
)
public class TbResultEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId("id")
  private Long id;

  /**
   */
  @TableField("alarm_end_time")
  private Date alarmEndTime;

  /**
   * 监控case报警原因描述
   */
  @TableField("alarm_reason_desc")
  private String alarmReasonDesc;

  /**
   * 监控case报警原因id
   */
  @TableField("alarm_reason_id")
  private Long alarmReasonId;

  /**
   * 监控case报警原因标签
   */
  @TableField("alarm_reason_tags")
  private String alarmReasonTags;

  /**
   */
  @TableField("alarm_start_time")
  private Date alarmStartTime;

  /**
   */
  @TableField("alarmed_at")
  private Date alarmedAt;

  /**
   * 废弃case总数
   */
  @TableField("case_abort_num")
  private Integer caseAbortNum;

  /**
   * case总数
   */
  @TableField("case_all_num")
  private Integer caseAllNum;

  /**
   * 异常case总数
   */
  @TableField("case_except_num")
  private Integer caseExceptNum;

  /**
   * 各domain执行case数
   */
  @TableField("case_executed_detail")
  private String caseExecutedDetail;

  /**
   * 已执行case总数
   */
  @TableField("case_executed_num")
  private Integer caseExecutedNum;

  /**
   * 失败case总数
   */
  @TableField("case_fail_num")
  private Integer caseFailNum;

  /**
   * 测试用例id
   */
  @TableField("case_id")
  private Long caseId;

  /**
   * 监控case来源信息
   */
  @TableField("case_source")
  private String caseSource;

  /**
   * 成功case总数
   */
  @TableField("case_succ_num")
  private Integer caseSuccNum;

  /**
   * 配置
   */
  @TableField("conf")
  private String conf;

  /**
   * 执行时间
   */
  @TableField("cost_time")
  private String costTime;

  /**
   * 创建时间
   */
  @TableField("created_at")
  private Date createdAt;

  /**
   * 删除时间
   */
  @TableField("deleted_at")
  private Date deletedAt;

  /**
   */
  @TableField("fixed_at")
  private Date fixedAt;

  /**
   * 产品线id
   */
  @TableField("group_id")
  private Long groupId;

  /**
   * 描述
   */
  @TableField("info")
  private String info;

  /**
   * 是否报警
   */
  @TableField("is_alarm")
  private Integer isAlarm;

  /**
   * 是否删除
   */
  @TableField("is_delete")
  private Integer isDelete;

  /**
   * jira 状态
   */
  @TableField("jira_status")
  private Integer jiraStatus;

  /**
   * jira id
   */
  @TableField("jira_tag")
  private String jiraTag;

  /**
   */
  @TableField("msg")
  private String msg;

  /**
   * 负责人
   */
  @TableField("owner_name")
  private String ownerName;

  /**
   * 产品线id
   */
  @TableField("plan_id")
  private Long planId;

  /**
   * 产品线id
   */
  @TableField("prod_id")
  private Long prodId;

  /**
   */
  @TableField("request_id")
  private String requestId;

  /**
   * 结果明细
   */
  @TableField("result_detail")
  private String resultDetail;

  /**
   * 产品线id
   */
  @TableField("result_id")
  private Long resultId;

  /**
   * 描述
   */
  @TableField("result_path")
  private String resultPath;

  /**
   * 状态
   */
  @TableField("status")
  private Integer status;

  /**
   * 类型 1：test 2:case 3:group 4:plan
   */
  @TableField("type")
  private Integer type;

  /**
   * 更新时间
   */
  @TableField("updated_at")
  private Date updatedAt;

  @Override
  public Serializable findPk() {
    return this.id;
  }

  @Override
  public Consumer<Long> pkSetter() {
    return this::setId;
  }

  @Override
  public final Class<? extends IEntity> entityClass() {
    return TbResultEntity.class;
  }

  @Override
  public final TbResultEntity changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final TbResultEntity changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }
}
