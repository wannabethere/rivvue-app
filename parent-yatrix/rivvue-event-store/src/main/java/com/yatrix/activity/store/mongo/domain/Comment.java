package com.yatrix.activity.store.mongo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yatrix.activity.store.fb.domain.FacebookReference;

@JsonIgnoreProperties(
	    ignoreUnknown = true)
public class Comment extends Item implements Serializable {

	public static enum COMMENTTYPE{
		APP,FB,TWT;
	}

	private static final long serialVersionUID = 1L;
	private String message;
	private long createdTime;
	private String fromId;
	private String fromAuthorName;
	private long lupd;
	private boolean deleted;
	private List<CommentSync> commentsSync=new ArrayList<CommentSync>();
	
	
	/**
	 * Need this for Spring Bean annotation
	 */
	public Comment(){
		super();
	}
	
	
	public Comment( String pMessage, long pCreatedDate, Participant pFrom){
		super();
		this.message = pMessage;
		this.createdTime = pCreatedDate;
		this.fromId = pFrom.getUserId();
		this.fromAuthorName=pFrom.getInviteeName();
	}
	
	public Comment( String pMessage, long pCreatedDate, String userId){
		super();
		this.message = pMessage;
		this.createdTime = pCreatedDate;
		this.fromId = userId;
	}
	
	public Comment(Comment comment){
		super.setId(comment.getId());
		this.message=comment.getMessage();
		this.fromId=comment.getFromId();
		this.fromAuthorName=comment.fromAuthorName;
		this.commentsSync.addAll(comment.commentsSync);
		this.lupd=comment.lupd;
		this.createdTime=comment.createdTime;
		this.deleted=comment.deleted;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String from) {
		this.fromId = from;
	}

	public long getLupd() {
		return lupd;
	}

	public void setLupd(long lupd) {
		this.lupd = lupd;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public String getFromAuthorName() {
		return fromAuthorName;
	}
	public void setFromAuthorName(String fromAuthorName) {
		this.fromAuthorName = fromAuthorName;
	}
	
	
	public List<CommentSync> getCommentSync() {
		return commentsSync;
	}
	public void setCommentSync(List<CommentSync> commentSync) {
		this.commentsSync = commentSync;
	}
	
	public void addCommentSync(String commentId, COMMENTTYPE type, long lupd){
		CommentSync commentSync= new CommentSync();
		commentSync.setCommentId(commentId);
		commentSync.setLupd(lupd);
		commentSync.setType(type);
		this.commentsSync.add(commentSync);
	}
	
	@Override
	public String toString() {
		return "Comment [message=" + message + ", createdTime=" + createdTime
				+ ", fromId=" + fromId + ", fromAuthorName=" + fromAuthorName
				+ ", lupd=" + lupd + ", deleted=" + deleted + ", commentsSync="
				+ commentsSync + "]";
	}
	
	public static class CommentSync{
		String commentId;
		COMMENTTYPE type;
		long lupd;
		
		public String getCommentId() {
			return commentId;
		}
		public void setCommentId(String commentId) {
			this.commentId = commentId;
		}
		public COMMENTTYPE getType() {
			return type;
		}
		public void setType(COMMENTTYPE type) {
			this.type = type;
		}
		public long getLupd() {
			return lupd;
		}
		public void setLupd(long lupd) {
			this.lupd = lupd;
		}
		@Override
		public String toString() {
			return "CommentSync [commentId=" + commentId + ", type=" + type
					+ ", lupd=" + lupd + "]";
		}
		
		
	}
	
}
