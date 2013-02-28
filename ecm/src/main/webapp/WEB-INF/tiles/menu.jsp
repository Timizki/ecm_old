<%@ taglib tagdir="/WEB-INF/tags" prefix="ecm" %>
<%@ taglib uri="/WEB-INF/ecmTags.tld" prefix="dev" %>
<div id="navigation">
	<dev:navigation var="levelRootItem" depth="3" sitemapItem="${sitemapItem}">
		<ul>
			<dev:navigationLevel var="navigationItem" sitemapItem="${levelRootItem}" activeSitemapItem="${sitemapItem}">
				<ecm:NavigationItem item="${navigationItem}" styleClass="${styleClass}" />
			</dev:navigationLevel>
		</ul>
	</dev:navigation> 
</div>