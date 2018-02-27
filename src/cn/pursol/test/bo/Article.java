package cn.pursol.test.bo;

/**
*<p>Title:文章</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.util.bo.BaseObject;

public class Article extends BaseObject implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private Integer cid;
	private String title;//标题
	private String author;
	private String createtime;
	private String text;//正文
	private Integer userid;
	private String state;
	private Integer classifyid;//分类id

	public Article(){
	}

 	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid=cid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title=title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author=author;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime=createtime;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text=text;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid=userid;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state=state;
	}

	public Integer getClassifyid() {
		return this.classifyid;
	}

	public void setClassifyid(Integer classifyid) {
		this.classifyid=classifyid;
	}

public String getId() {
return String.valueOf(this.cid);
}
	public String toString() {
		return new ToStringBuilder(this)
		.append("cid",getCid()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Article))
		return false;
		Article castOther = (Article)other;
		return new EqualsBuilder().append(cid, castOther.cid).isEquals();
	}

public int hashCode() {
		return new HashCodeBuilder().append(cid).toHashCode();
	}

}