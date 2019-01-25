package com.lgcns.login.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable {	 
		private static final long serialVersionUID = -3009157732242241606L;
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
	 
		@Column(name = "userid")
		private String userId;
		
		@Column(name = "firstname")
		private String firstName;
	 
		@Column(name = "lastname")
		private String lastName;
	 
		protected Users() {
		}
	 
		
		public Users(String userId, String firstName, String lastName) {
			this.userId = userId;
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName() {
			return this.firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return this.lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getUserId() {
			return this.userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
		@Override
		public String toString() {
			return String.format("Users[id=%d, userId='%s', firstName='%s', lastName='%s']", id, userId, firstName, lastName);
		}
	}
