
package com.proliferay.sbuilder.example.custom.field.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.expando.model.ExpandoRow;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.proliferay.sbuilder.example.custom.field.model.Book;
import com.proliferay.sbuilder.example.custom.field.service.base.BookLocalServiceBaseImpl;

/**
 * The implementation of the book local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.proliferay.sbuilder.example.custom.field.service.BookLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Hamidul Islam
 * @see com.proliferay.sbuilder.example.custom.field.service.base.BookLocalServiceBaseImpl
 * @see com.proliferay.sbuilder.example.custom.field.service.BookLocalServiceUtil
 */
public class BookLocalServiceImpl extends BookLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.proliferay.sbuilder.example.custom.field.service.BookLocalServiceUtil} to access the book local service.
	 */
	
	public Book addCustomBook(Book book, ServiceContext serviceContext) throws PortalException, SystemException{
		
		long bookId = counterLocalService.increment();
		book.setBookId(bookId);
		book = bookLocalService.addBook(book);
		if (serviceContext != null) {
			book.setExpandoBridgeAttributes(serviceContext);
			book = bookPersistence.update(book);
		}
		return book;
	}
	
public void deleteCustomBook(long bookId) throws PortalException, SystemException{ 
		
		ClassName className = classNameLocalService.getClassName(Book.class.getName());
		ExpandoTable expandoTable = expandoTableLocalService.getDefaultTable(CompanyThreadLocal.getCompanyId(), className.getClassNameId());
		
		/**
		* Delete ExpandoRow before deleteting Book Entitity 
		* If we delete row, corresponding values will be also deleted
		* In the below code expandoRowPersistence.fetchByT_C will return null if there is no row
		*/
		
		ExpandoRow expandoRow = expandoRowPersistence.fetchByT_C(expandoTable.getTableId(), bookId);
		
		if(expandoRow !=null){
			expandoRowLocalService.deleteRow(expandoRow); 
		}
		
		bookLocalService.deleteBook(bookId);
	}
	
public Book updateCustomBook(Book book, ServiceContext serviceContext) throws PortalException, SystemException{
		
		book = bookLocalService.updateBook(book);
		if (serviceContext != null) {
			book.setExpandoBridgeAttributes(serviceContext);
			book = bookPersistence.update(book);
		}
		return book;
	}
}