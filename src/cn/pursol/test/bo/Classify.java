package cn.pursol.test.bo;

/**
*<p>Title:分类</p>
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

public class Classify extends BaseObject implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private Integer classifyid;
	private String title;
	private String createtime;
	private String text;
	private String state;

	public Classify(){
	}

 	public Integer getClassifyid() {
		return this.classifyid;
	}

	public void setClassifyid(Integer classifyid) {
		this.classifyid=classifyid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title=title;
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

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state=state;
	}

public String getId() {
return String.valueOf(this.classifyid);
}
	public String toString() {
		return new ToStringBuilder(this)
		.append("classifyid",getClassifyid()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Classify))
		return false;
		Classify castOther = (Classify)other;
		return new EqualsBuilder().append(classifyid, castOther.classifyid).isEquals();
	}

public int hashCode() {
		return new HashCodeBuilder().append(classifyid).toHashCode();
	}

}