<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %> 
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@page import="com.liferay.portlet.expando.model.ExpandoBridge"%>
<%@page import="com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil"%>
<%@page import="com.proliferay.sbuilder.example.custom.field.model.impl.BookImpl"%>
<%@page import="com.proliferay.sbuilder.example.custom.field.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@page import="com.proliferay.util.WebKeys"%>
<%@page import="com.proliferay.sbuilder.example.custom.field.model.Book"%>
<%@page import="com.proliferay.sbuilder.example.custom.field.service.BookLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>


<portlet:defineObjects />
<theme:defineObjects/>