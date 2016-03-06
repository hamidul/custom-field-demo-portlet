package com.proliferay.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.ProcessAction;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.proliferay.sbuilder.example.custom.field.model.Book;
import com.proliferay.sbuilder.example.custom.field.service.BookLocalServiceUtil;
import com.proliferay.util.ActionUtil;
import com.proliferay.util.WebKeys;

/**
 * 
 * @author Hamidul Islam
 *
 */

public class BookPortlet extends MVCPortlet{
	

	/**
	 * 
	 * This method will persist the data in database
	 * @throws PortalException 
	 */
	@ProcessAction(name = "addBook")
	public void addBook(ActionRequest actionRequest,ActionResponse actionResponse) throws SystemException, PortalException {
		

			ServiceContext serviceContext = ServiceContextFactory.getInstance(Book.class.getName(), actionRequest);
			Book book = ActionUtil.bookFromRequest(actionRequest);
			
			
			//Calling service method to persist book.
			BookLocalServiceUtil.addCustomBook(book, serviceContext);
			
			
			SessionMessages.add(actionRequest, "added-book"); 
		   _log.info("#################Added Book Successfully#########################");
		
	}
	
	@ProcessAction(name = "deleteBook")
	public void deleteBook(ActionRequest actionRequest,ActionResponse actionResponse) {
		
		try {
			long bookId = ParamUtil.getLong(actionRequest, "bookId");
			BookLocalServiceUtil.deleteCustomBook(bookId);
			SessionMessages.add(actionRequest, "deleted-book"); 
			_log.info("#################Book Deleted Successfully#########################");
		} catch (PortalException e) {
			_log.error(e);
		} catch (SystemException e) {
			_log.error(e);
		}
	
		
	}
	
	@ProcessAction(name = "viewBook")
	public void viewBook(ActionRequest actionRequest,ActionResponse actionResponse){
		
		try {
			long bookId = ParamUtil.getLong(actionRequest, "bookId");
			 
			 Book book = BookLocalServiceUtil.getBook(bookId);
			actionRequest.setAttribute(WebKeys.BOOK_ENTRY, book);
		} catch (PortalException e) {
			_log.error(e);
		} catch (SystemException e) {
			_log.error(e);
		}
	
		//Show view_book.jsp
		actionResponse.setRenderParameter("jspPage", "/html/view_book.jsp");
	}
	
	@ProcessAction(name = "updateBook")
	public void updateBook(ActionRequest actionRequest,ActionResponse actionResponse) throws SystemException, PortalException {
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Book.class.getName(), actionRequest);
		long bookId = (Long) actionRequest.getPortletSession().getAttribute(WebKeys.BOOK_ID,PortletSession.PORTLET_SCOPE);
		Book book = ActionUtil.bookFromRequest(actionRequest);
		book.setBookId(bookId);
		book.setExpandoBridgeAttributes(serviceContext);
		/**
		 * Calling service method to update book.
		 * While calling update method we must have primary key in the passing object.
		 */
		BookLocalServiceUtil.updateBook(book);
	
		SessionMessages.add(actionRequest, "updated-book"); 
		_log.info("#################Updated Book Successfully#########################");
		
	}
	
	@ProcessAction(name = "viewEdit")
	public void viewEdit(ActionRequest actionRequest,ActionResponse actionResponse) throws SystemException, PortalException {
		long bookId = ParamUtil.getLong(actionRequest, "bookId",0);
		Book book = BookLocalServiceUtil.getBook(bookId);
		actionRequest.setAttribute(WebKeys.BOOK, book); 
		//Show edit_book.jsp
		actionResponse.setRenderParameter("jspPage", "/html/edit_book.jsp");
		actionRequest.getPortletSession().setAttribute(WebKeys.BOOK_ID, book.getBookId(),PortletSession.PORTLET_SCOPE);
			
	}
	private Log _log = LogFactoryUtil.getLog(BookPortlet.class.getName());

}
