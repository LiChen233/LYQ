package com.beneway.common.lyq.entity.rate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-28 21:13:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("Rate")
public class Rate implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 作业id
	 */
	private String workId;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 系统研判分数
	 */
	private Integer rateAuto;
	/**
	 * 老师打分
	 */
	private Integer rate;
	/**
	 * 额外作业
	 */
	private String files;

}
