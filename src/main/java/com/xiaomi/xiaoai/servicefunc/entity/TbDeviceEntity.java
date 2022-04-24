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
 * TbDeviceEntity: 数据映射实体定义
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
    table = "tb_device",
    schema = "db_monitor"
)
public class TbDeviceEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId("id")
  private Integer id;

  /**
   * 监控设备的名称
   */
  @TableField("app")
  private String app;

  /**
   * 设备唯一标识
   */
  @TableField("app_id")
  private String appId;

  /**
   * 设备应用场景
   */
  @TableField("category")
  private String category;

  /**
   * 设备监控配置
   */
  @TableField("conf")
  private String conf;

  /**
   * 创建时间
   */
  @TableField("created_at")
  private Date createdAt;

  /**
   * 创建人
   */
  @TableField("creator_id")
  private Integer creatorId;

  /**
   * 中文名字
   */
  @TableField("creator_name")
  private String creatorName;

  /**
   * 删除时间
   */
  @TableField("deleted_at")
  private Date deletedAt;

  /**
   * 设备监控的domain
   */
  @TableField("domains")
  private String domains;

  /**
   * 设备描述
   */
  @TableField("info")
  private String info;

  /**
   * 是否删除
   */
  @TableField("is_delete")
  private Integer isDelete;

  /**
   */
  @TableField("is_monitor")
  private Integer isMonitor;

  /**
   * 设备请求参数
   */
  @TableField("params")
  private String params;

  /**
   * 接口id
   */
  @TableField("prod_id")
  private Long prodId;

  /**
   * 设备特征类型
   */
  @TableField("type")
  private Integer type;

  /**
   * 更新时间
   */
  @TableField("updated_at")
  private Date updatedAt;

  /**
   * 最后的修改人
   */
  @TableField("updater_name")
  private String updaterName;

  /**
   */
  @TableField("version")
  private String version;

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
    return TbDeviceEntity.class;
  }

  @Override
  public final TbDeviceEntity changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final TbDeviceEntity changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }
}
