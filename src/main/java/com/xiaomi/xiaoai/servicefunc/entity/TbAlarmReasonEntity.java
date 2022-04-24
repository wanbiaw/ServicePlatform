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
 * TbAlarmReasonEntity: 数据映射实体定义
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
    table = "tb_alarm_reason",
    schema = "db_monitor"
)
public class TbAlarmReasonEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId("id")
  private Long id;

  /**
   * 创建时间
   */
  @TableField("created_at")
  private Date createdAt;

  /**
   * 创建人
   */
  @TableField("creator_name")
  private String creatorName;

  /**
   * 是否必现问题（默认值为1）
   */
  @TableField("is_reproduce")
  private Integer isReproduce;

  /**
   * 是否有效告警（默认值为1）
   */
  @TableField("is_valid")
  private Integer isValid;

  /**
   * 原因类型叶子节点
   */
  @TableField("reason_subtype")
  private String reasonSubtype;

  /**
   * 原因类型根节点
   */
  @TableField("reason_type")
  private String reasonType;

  /**
   * 解决问题类型
   */
  @TableField("solve_type")
  private String solveType;

  /**
   * 原因状态，状态码参考case和设备，保持一致
   */
  @TableField("status")
  private Integer status;

  /**
   * 最后修改时间
   */
  @TableField("updated_at")
  private Date updatedAt;

  /**
   * 最后修改人
   */
  @TableField("updater_name")
  private String updaterName;

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
    return TbAlarmReasonEntity.class;
  }

  @Override
  public final TbAlarmReasonEntity changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final TbAlarmReasonEntity changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }
}
