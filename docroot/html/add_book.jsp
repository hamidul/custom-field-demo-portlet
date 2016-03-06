
<%@ include file="/html/init.jsp" %> 
<%
Book book = new BookImpl();

ExpandoBridge  expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(themeDisplay.getCompanyId(), Book.class.getName());

List<String> attributeList = Collections.list(expandoBridge.getAttributeNames()); 

%> 

<portlet:actionURL name="addBook" var="addBookURL" />

<portlet:renderURL windowState="normal" var="backURL">
  <portlet:param name="jspPage" value="/html/view.jsp"></portlet:param>
</portlet:renderURL>

<liferay-ui:header
    backURL="<%= backURL %>"
    title="Back"
/>


<aui:form name="fm" action="<%=addBookURL.toString()%>" method="post">

	<aui:input name="bookName" label="Book Name"/>
	<aui:input type="textarea" name="description" label="Description"/>
	<aui:input name="authorName" label="Author Name"/>
	<aui:input name="isbn" label="ISBN">
		 <aui:validator name="digits"/>
	</aui:input>
	<aui:input name="price" label="Price">
		 <aui:validator name="digits"/>
	</aui:input>
	
	<%if(attributeList.size() != 0){ %>
	
		<liferay-ui:header title="Custom Fields"/>
	
	<%} %>
	
	<liferay-ui:custom-attributes-available   className="<%=Book.class.getName() %>"> 
                        <liferay-ui:custom-attribute-list
                                        className="<%= Book.class.getName() %>"
                                        classPK="<%= (book != null) ? book.getBookId() : 0 %>"
                                        editable="<%= true %>"
                                        label="<%= true %>"
                                />
      </liferay-ui:custom-attributes-available>
	
	<aui:button-row>
        <aui:button name="addBook" type="submit" value="Add Book"  />
    </aui:button-row>
		
</aui:form>