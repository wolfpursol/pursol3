package cn.pursol.test.bo;

/**
*<p>Title:用户</p>
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

public class User extends BaseObject implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer age;
	private String createtime;
	private String state;
	public User(){
	}

 	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id=id;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age=age;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime=createtime;
	}

	public String toString() {
		return new ToStringBuilder(this)
		.append("id",getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof User))
		return false;
		User castOther = (User)other;
		return new EqualsBuilder().append(id, castOther.id).isEquals();
	}

public int hashCode() {
		return new HashCodeBuilder().append(id).toHashCode();
	}

}