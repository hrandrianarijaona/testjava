<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
<html>
<body>
	<h2>Thumbnail generator</h2>
	<fieldset>
		<springForm:form action="thumbnailsGen" commandName="thumbnailRequest" method="POST">
			<table>
				<tr>
					<td>Width:</td>
					<td><springForm:input path="width" /></td>
					<td><springForm:errors path="width" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Height:</td>
					<td><springForm:input path="height" /></td>
					<td><springForm:errors path="height" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Limit:</td>
					<td><springForm:input path="limit" /></td>
					<td><springForm:errors path="limit" cssClass="error" /></td>
				</tr>
				<tr>
					<td><button type="submit">Submit</button></td>
				</tr>
			</table>
		</springForm:form>

	</fieldset>
</body>
</html>
