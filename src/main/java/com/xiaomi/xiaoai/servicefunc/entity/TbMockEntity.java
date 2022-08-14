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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TbMockEntity: 数据映射实体定义
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
    table = "tb_mock",
    schema = "db_monitor"
)
public class TbMockEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId("id")
  private Integer id;

  /**
   * 创建时间
   */
  @TableField("createdAt")
  private Date createdAt;

  /**
   * 延迟响应
   */
  @TableField("delay")
  private Integer delay;

  /**
   * 请求头信息
   */
  @TableField("header")
  private String header;

  /**
   * mock名称
   */
  @TableField("name")
  private String name;

  /**
   * 请求参数
   */
  @TableField("param")
  private String param;

  /**
   * post请求体数据
   */
  @TableField("requestData")
  private String requestData;

  /**
   * 槽位
   */
  @TableField("requestMethod")
  private String requestMethod;

  /**
   * 预期响应数据
   */
  @TableField("response")
  private String response;

  /**
   * 保留字段
   */
  @TableField("reverse")
  private String reverse;

  /**
   * 状态码
   */
  @TableField("status")
  private Integer status;

  /**
   * 更新时间
   */
  @TableField("updatedAt")
  private Date updatedAt;

  /**
   * mock地址
   */
  @TableField("url")
  private String url;

  /**
   * mock服务器uuid
   */
  @TableField("uuid")
  private String uuid;

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
    return TbMockEntity.class;
  }

  @Override
  public final TbMockEntity changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final TbMockEntity changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }
}
