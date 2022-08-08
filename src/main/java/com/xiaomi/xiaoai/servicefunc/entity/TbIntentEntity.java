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
 * TbIntentEntity: 数据映射实体定义
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
    table = "tb_intent",
    schema = "db_monitor"
)
public class TbIntentEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId("id")
  private Integer id;

  /**
   */
  @TableField("created_at")
  private Date createdAt;

  /**
   * 设备信息
   */
  @TableField("deviceInfo")
  private String deviceInfo;

  /**
   * 环境配置
   */
  @TableField("envs")
  private String envs;

  /**
   * 意图信息
   */
  @TableField("intent")
  private String intent;

  /**
   * 发布槽位
   */
  @TableField("publishedSlots")
  private String publishedSlots;

  /**
   * 屏幕大小
   */
  @TableField("screen")
  private String screen;

  /**
   * 订阅技能
   */
  @TableField("skill")
  private String skill;

  /**
   * 槽位
   */
  @TableField("slots")
  private String slots;

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
    return TbIntentEntity.class;
  }

  @Override
  public final TbIntentEntity changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final TbIntentEntity changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }
}
