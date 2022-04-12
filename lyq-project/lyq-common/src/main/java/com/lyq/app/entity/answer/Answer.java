package com.lyq.app.entity.answer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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
@TableName("answer")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
					@TableId(value = "id", type = IdType.ASSIGN_ID)
						private String id;
	/**
	 * 学生id
	 */
		private String studentId;
	/**
	 * 作业id
	 */
		private String workId;
	/**
	 * 答题id
	 */
		private String topicCheckId;
	/**
	 * 附加作业文件id
	 */
		private String filesId;
	/**
	 * 自动判定分数
	 */
		private Integer creditAuto;
	/**
	 * 老师判定分数
	 */
		private Integer credit;
	/**
	 * 时间
	 */
			@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

}
