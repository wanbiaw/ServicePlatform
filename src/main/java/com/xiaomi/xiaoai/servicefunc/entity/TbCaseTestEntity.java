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
 * TbCaseTestEntity: 数据映射实体定义
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
    table = "tb_case_test",
    schema = "db_monitor"
)
public class TbCaseTestEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   */
  @TableId("id")
  private Long id;

  /**
   * 报警策略
   */
  @TableField("alarm_strategy")
  private String alarmStrategy;

  /**
   */
  @TableField("case_id")
  private Long caseId;

  /**
   * 配置
   */
  @TableField("conf")
  private String conf;

  /**
   * 常量
   */
  @TableField("constant")
  private String constant;

  /**
   * case内容
   */
  @TableField("content")
  private String content;

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
   * 等级
   */
  @TableField("degree")
  private Integer degree;

  /**
   * 删除时间
   */
  @TableField("deleted_at")
  private Date deletedAt;

  /**
   */
  @TableField("device_type")
  private Integer deviceType;

  /**
   * 最终状态 1: 未初始化 2:测试中 3:已上线 4:下线
   */
  @TableField("final_status")
  private Integer finalStatus;

  /**
   * 1:定制 2:RFM
   */
  @TableField("frame")
  private Integer frame;

  /**
   * 功能点描述
   */
  @TableField("function_desc")
  private String functionDesc;

  /**
   * 功能点id
   */
  @TableField("function_id")
  private Long functionId;

  /**
   * 功能点名称
   */
  @TableField("function_name")
  private String functionName;

  /**
   * 状态 1: 未初始化 2:测试中 3:已上线 4:下线
   */
  @TableField("function_status")
  private Integer functionStatus;

  /**
   * 描述
   */
  @TableField("info")
  private String info;

  /**
   * 是否报警 1: 否 2:是
   */
  @TableField("is_alarm")
  private Integer isAlarm;

  /**
   * 是否删除 1: 未删除 2：已删除
   */
  @TableField("is_delete")
  private Integer isDelete;

  /**
   * 是否支持并发 1: 不支持 2:支持
   */
  @TableField("is_paralle")
  private Integer isParalle;

  /**
   * 负责人
   */
  @TableField("owner_id")
  private Integer ownerId;

  /**
   * 负责人名称
   */
  @TableField("owner_name")
  private String ownerName;

  /**
   * 上层id
   */
  @TableField("parent_id")
  private Integer parentId;

  /**
   * 产品线id
   */
  @TableField("prod_id")
  private Integer prodId;

  /**
   * query
   */
  @TableField("query")
  private String query;

  /**
   * 组序
   */
  @TableField("rank_id")
  private Integer rankId;

  /**
   * 1:上下文继承 2:分组管理
   */
  @TableField("relation")
  private Integer relation;

  /**
   * 根id(同一group相同)
   */
  @TableField("root_id")
  private Long rootId;

  /**
   */
  @TableField("run_env")
  private String runEnv;

  /**
   */
  @TableField("section_id")
  private Long sectionId;

  /**
   * 监控case来源详细信息
   */
  @TableField("source_detail")
  private String sourceDetail;

  /**
   * 来源类型（默认值为0）
   */
  @TableField("source_type")
  private Integer sourceType;

  /**
   * 状态 1: 未初始化 2:测试中 3:已上线 4:下线
   */
  @TableField("status")
  private Integer status;

  /**
   * 支持列表
   */
  @TableField("supports")
  private String supports;

  /**
   * 标签
   */
  @TableField("tags")
  private String tags;

  /**
   * case转换
   */
  @TableField("transform")
  private String transform;

  /**
   * 类型，1：test 2:case 3:group
   */
  @TableField("type")
  private Integer type;

  /**
   * 更新时间
   */
  @TableField("updated_at")
  private Date updatedAt;

  /**
   */
  @TableField("updater_id")
  private Integer updaterId;

  /**
   * 最后的修改人
   */
  @TableField("updater_name")
  private String updaterName;

  /**
   * 有效时间
   */
  @TableField("valid_time")
  private String validTime;

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
    return TbCaseTestEntity.class;
  }

  @Override
  public final TbCaseTestEntity changeTableBelongTo(TableSupplier supplier) {
    return super.changeTableBelongTo(supplier);
  }

  @Override
  public final TbCaseTestEntity changeTableBelongTo(String table) {
    return super.changeTableBelongTo(table);
  }
}
