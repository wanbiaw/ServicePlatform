package com.xiaomi.xiaoai.servicefunc.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import cn.org.atool.fluent.mybatis.functions.TableSupplier;
import java.io.Serializable;
import java.util.function.Consumer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * TbMonitorDashboardEntity: 数据映射实体定义
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
    table = "tb_monitor_dashboard",
    schema = "db_monitor"
)
public class TbMonitorDashboardEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId("id")
  private Integer id;

  /**
   * 监控报警详情
   */
  @TableField("alarms_num")
  private String alarmsNum;

  /**
   * 拦截bug数详情
   */
  @TableField("bugs_num")
  private String bugsNum;

  /**
   * 监控用例详情
   */
  @TableField("cases_num")
  private String casesNum;

  /**
   * 覆盖率指标详情
   */
  @TableField("cover_rate")
  private String coverRate;

  /**
   * 统计日期
   */
  @TableField("day")
  private Integer day;

  /**
   * 事故处理平均时间详情
   */
  @TableField("handle_time")
  private String handleTime;

  /**
   * 统计月份
   */
  @TableField("month")
  private Integer month;

  /**
   * 模拟请求数详情
   */
  @TableField("req_num")
  private String reqNum;

  /**
   * 保留字段
   */
  @TableField("reverse")
  private String reverse;

  /**
   * 统计年份
   */
  @TableField("year")
  private Integer year;

  @Override
  public Serializable findPk() {
    return this.id;
  }

  @Override
  public Consumer<Integer> pkSetter() {
    return this::setId;
  }

  @Override
  public final Class<? extends IEntity> entityClass() {
    return TbMonitorDashboardEntity.class;
  }

  @Override
  public final TbMonitorDashboardEntity changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final TbMonitorDashboardEntity changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }
}
