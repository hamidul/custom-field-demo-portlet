/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.proliferay.sbuilder.example.custom.field.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.proliferay.sbuilder.example.custom.field.service.BookLocalServiceUtil;
import com.proliferay.sbuilder.example.custom.field.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hamidul Islam
 */
public class BookClp extends BaseModelImpl<Book> implements Book {
	public BookClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Book.class;
	}

	@Override
	public String getModelClassName() {
		return Book.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _bookId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBookId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _bookId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("bookId", getBookId());
		attributes.put("bookName", getBookName());
		attributes.put("description", getDescription());
		attributes.put("authorName", getAuthorName());
		attributes.put("isbn", getIsbn());
		attributes.put("price", getPrice());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long bookId = (Long)attributes.get("bookId");

		if (bookId != null) {
			setBookId(bookId);
		}

		String bookName = (String)attributes.get("bookName");

		if (bookName != null) {
			setBookName(bookName);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String authorName = (String)attributes.get("authorName");

		if (authorName != null) {
			setAuthorName(authorName);
		}

		Integer isbn = (Integer)attributes.get("isbn");

		if (isbn != null) {
			setIsbn(isbn);
		}

		Integer price = (Integer)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}
	}

	@Override
	public long getBookId() {
		return _bookId;
	}

	@Override
	public void setBookId(long bookId) {
		_bookId = bookId;

		if (_bookRemoteModel != null) {
			try {
				Class<?> clazz = _bookRemoteModel.getClass();

				Method method = clazz.getMethod("setBookId", long.class);

				method.invoke(_bookRemoteModel, bookId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getBookName() {
		return _bookName;
	}

	@Override
	public void setBookName(String bookName) {
		_bookName = bookName;

		if (_bookRemoteModel != null) {
			try {
				Class<?> clazz = _bookRemoteModel.getClass();

				Method method = clazz.getMethod("setBookName", String.class);

				method.invoke(_bookRemoteModel, bookName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_bookRemoteModel != null) {
			try {
				Class<?> clazz = _bookRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_bookRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAuthorName() {
		return _authorName;
	}

	@Override
	public void setAuthorName(String authorName) {
		_authorName = authorName;

		if (_bookRemoteModel != null) {
			try {
				Class<?> clazz = _bookRemoteModel.getClass();

				Method method = clazz.getMethod("setAuthorName", String.class);

				method.invoke(_bookRemoteModel, authorName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getIsbn() {
		return _isbn;
	}

	@Override
	public void setIsbn(int isbn) {
		_isbn = isbn;

		if (_bookRemoteModel != null) {
			try {
				Class<?> clazz = _bookRemoteModel.getClass();

				Method method = clazz.getMethod("setIsbn", int.class);

				method.invoke(_bookRemoteModel, isbn);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getPrice() {
		return _price;
	}

	@Override
	public void setPrice(int price) {
		_price = price;

		if (_bookRemoteModel != null) {
			try {
				Class<?> clazz = _bookRemoteModel.getClass();

				Method method = clazz.getMethod("setPrice", int.class);

				method.invoke(_bookRemoteModel, price);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getBookRemoteModel() {
		return _bookRemoteModel;
	}

	public void setBookRemoteModel(BaseModel<?> bookRemoteModel) {
		_bookRemoteModel = bookRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _bookRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_bookRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			BookLocalServiceUtil.addBook(this);
		}
		else {
			BookLocalServiceUtil.updateBook(this);
		}
	}

	@Override
	public Book toEscapedModel() {
		return (Book)ProxyUtil.newProxyInstance(Book.class.getClassLoader(),
			new Class[] { Book.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		BookClp clone = new BookClp();

		clone.setBookId(getBookId());
		clone.setBookName(getBookName());
		clone.setDescription(getDescription());
		clone.setAuthorName(getAuthorName());
		clone.setIsbn(getIsbn());
		clone.setPrice(getPrice());

		return clone;
	}

	@Override
	public int compareTo(Book book) {
		long primaryKey = book.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BookClp)) {
			return false;
		}

		BookClp book = (BookClp)obj;

		long primaryKey = book.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{bookId=");
		sb.append(getBookId());
		sb.append(", bookName=");
		sb.append(getBookName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", authorName=");
		sb.append(getAuthorName());
		sb.append(", isbn=");
		sb.append(getIsbn());
		sb.append(", price=");
		sb.append(getPrice());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.proliferay.sbuilder.example.custom.field.model.Book");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>bookId</column-name><column-value><![CDATA[");
		sb.append(getBookId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>bookName</column-name><column-value><![CDATA[");
		sb.append(getBookName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>authorName</column-name><column-value><![CDATA[");
		sb.append(getAuthorName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>isbn</column-name><column-value><![CDATA[");
		sb.append(getIsbn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>price</column-name><column-value><![CDATA[");
		sb.append(getPrice());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _bookId;
	private String _bookName;
	private String _description;
	private String _authorName;
	private int _isbn;
	private int _price;
	private BaseModel<?> _bookRemoteModel;
	private Class<?> _clpSerializerClass = com.proliferay.sbuilder.example.custom.field.service.ClpSerializer.class;
}