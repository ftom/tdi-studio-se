<?xml version="1.0" encoding="utf-8"?>
<!-- edited with XMLSpy v2007 sp1 (http://www.altova.com) by  () -->
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:variable name="ucase"
			select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />
		<xsl:variable name="lcase"
			select="'abcdefghijklmnopqrstuvwxyz'" />
		<html>
			<head>
				<style>
					SPAN.special { font:12pt black; } TABLE.properties {
					width:95%; } TD.propname { width:30%; font:bold; }
					TR.profont{ font:bold; }

					TD.propval { width:70%; } TD.dependtype { width:20%;
					} TD.dependloc { width:60%; } TABLE.cols {
					width:90%; } TD.constraint { width:20%; font:bold; }
					H3.hand { cursor:hand; } img.bordercolor {
					border-color:#AFCA00; } .FONTSTYLE {font-family:
					Arial, Helvetica, sans-serif;} .LINKSTYLE
					{TEXT-DECORATION:none}a:hover{TEXT-DECORATION:underline}
					.TITLESTYLE {font-size: 26px; color: #818181;}
					.TOPTITLESTYLE{font-size: 40px; color: #818181;}
					.TABLECOLUMNSTYLE {font-family: Arial, Helvetica,
					sans-serif; color: #818181; background-color:
					#E6E6E6;align: center}

				</style>
			</head>
			<body>
				<br />
				<table border="0" cellspacing="0" rowspacing="0"
					width="90%" class="FONTSTYLE">
					<tr valign="top">
						<!--<td width="15%" rowspan="2" align="center"></td> -->
						<td width="26%" rowspan="2" align="center">
							<a href="#{project/@link}">
								<img src="{project/@logo}" border="0"
									align="absbottom" />
							</a>
						</td>
						<!-- <td width="1%" rowspan="2" align="center"></td> -->
						<td width="58%">
							<strong>
								<font class="TOPTITLESTYLE">
									Job&#160;documentation
								</font>
							</strong>
						</td>
					</tr>
					<tr valign="top">
						<td height="20" align="left" valign="top"
							class="TITLESTYLE">
							<xsl:value-of select="/project/@title" />
						</td>
					</tr>
				</table>
				<br />
				<br />
				<table border="1" width="90%" cellpadding="0"
					cellspacing="0" style="border-collapse: collapse"
					bordercolor="#111111" class="FONTSTYLE">
					<tr valign="top">
						<td align="center" width="25%"
							class="TABLECOLUMNSTYLE">
							PROJECT NAME
						</td>
						<td align="center" width="25%"
							class="FONTSTYLE">
							<xsl:value-of select="/project/@name" />
						</td>
						<td align="center" width="25%"
							class="TABLECOLUMNSTYLE">
							GENERATION DATE
						</td>
						<td align="center" width="25%">
							<xsl:value-of
								select="/project/@generatedDate" />
						</td>
						<tr>
							<td align="center" width="25%"
								class="TABLECOLUMNSTYLE">
								AUTHOR
							</td>
							<td align="center" width="25%">
								<xsl:value-of
									select="/project/job/@author" />
							</td>
							<td align="center" width="25%"
								class="TABLECOLUMNSTYLE">
								<xsl:value-of
									select="/project/@versionName" />
							</td>
							<td align="center" width="25%"
								class="FONTSTYLE">
								<xsl:value-of
									select="/project/@version" />
							</td>
						</tr>
					</tr>
				</table>
				<h2 class="FONTSTYLE">Summary</h2>
				<div class="FONTSTYLE">
					<b>
						<a href="#ProjectDescription">
							Project Description
						</a>
					</b>
				</div>
				<div class="FONTSTYLE">
					<b>
						<a href="#JobDescription">Job Description</a>
					</b>
				</div>
				<div class="FONTSTYLE">
					<b>
						<a href="#JobPreviewPicture">
							Job Preview Picture
						</a>
					</b>
				</div>
				<div class="FONTSTYLE">
					<b>
						<a href="#ComponentList">Component List</a>
					</b>
				</div>
				<div class="FONTSTYLE">
					<b>
						<a href="#ComponentsDescription">
							Components Description
						</a>
					</b>
				</div>
				<br />
				<br />
				<!-- Project Description-->
				<!--HR-->
				<h2 class="FONTSTYLE">
					<a id="ProjectDescription"
						name="#ProjectDescription">
						Project Description
					</a>
				</h2>
				<br />
				<table border="1" width="90%" class="FONTSTYLE"
					cellpadding="0" cellspacing="0" style="border-collapse: collapse"
					bordercolor="#111111">
					<th align="center" width="30%"
						class="TABLECOLUMNSTYLE">
						Properties
					</th>
					<th align="left" class="TABLECOLUMNSTYLE">
						&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Values
					</th>
					<tr>
						<td class="FONTSTYLE" align="center">Name</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of select="/project/@name" />
						</td>
					</tr>
					<tr>
						<td class="FONTSTYLE" align="center">
							Language
						</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of select="/project/@language" />
						</td>
					</tr>
					<tr>
						<td class="FONTSTYLE" align="center">
							Description
						</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of
								select="/project/@description" />
						</td>
					</tr>
				</table>
				<br />
				<br />
				<xsl:variable name="job" select="/project/job" />
				<h2 class="FONTSTYLE">
					<a id="JobDescription" name="#JobDescription">
						Job Description
					</a>
				</h2>
				<br />
				<table border="1" width="90%" cellpadding="0"
					cellspacing="0" style="border-collapse: collapse"
					bordercolor="#111111" frame="box">
					<th align="center" width="30%"
						class="TABLECOLUMNSTYLE">
						Properties
					</th>
					<th align="left" class="TABLECOLUMNSTYLE">
						&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Values
					</th>
					<tr>
						<td class="FONTSTYLE" align="center">Name</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of select="$job/@name" />
						</td>
					</tr>
					<tr>
						<td class="FONTSTYLE" align="center">Author</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of select="$job/@author" />
						</td>
					</tr>
					<tr>
						<td class="FONTSTYLE" align="center">
							Version
						</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of select="$job/@version" />
						</td>
					</tr>
					<tr>
						<td class="FONTSTYLE" align="center">
							Purpose
						</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of select="$job/@purpose" />
						</td>
					</tr>
					<tr>
						<td class="FONTSTYLE" align="center">Status</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of select="$job/@status" />
						</td>
					</tr>
					<tr>
						<td class="FONTSTYLE" align="center">
							Description
						</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of select="$job/@description" />
						</td>
					</tr>
					<tr>
						<td class="FONTSTYLE" align="center">
							Creation
						</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of select="$job/@creation" />
						</td>
					</tr>
					<tr>
						<td class="FONTSTYLE" align="center">
							Modification
						</td>
						<td class="FONTSTYLE" align="left">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<xsl:value-of select="$job/@modification" />
						</td>
					</tr>
				</table>
				<br />
				<!-- Project Description-->
				<!--HR-->
				<xsl:variable name="jobPreviewPicture"
					select="$job/preview/@picture" />
				<xsl:if test="string-length($jobPreviewPicture)!=0">
					<h2 class="FONTSTYLE">
						<a id="JobPreviewPicture"
							name="#JobPreviewPicture">
							Job Preview Picture
						</a>
					</h2>
					<br />
					<div class="FONTSTYLE" align="center">
						<img src="{$jobPreviewPicture}" usemap="#jobmap"
							alt="No image available" class="bordercolor" />
					</div>
				</xsl:if>
				<br />
				<br />
				<!-- Component List-->
				<!--HR-->
				<h2 class="FONTSTYLE">
					<a id="ComponentList" name="#ComponentList">
						Component List
					</a>
				</h2>
				<table width="90%" border="1" cellpadding="0"
					cellspacing="0" style="border-collapse: collapse"
					bordercolor="#111111" frame="box">
					<tr>
						<th width="50%" align="center"
							class="TABLECOLUMNSTYLE">
							Component Name
						</th>
						<th width="50%" align="center"
							class="TABLECOLUMNSTYLE">
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Component
							Type
						</th>
					</tr>
					<map id="jobmap" name="jobmap">
						<xsl:for-each
							select="$job/componentList/componentItem">
							<area shape="rect"
								coords="{@leftTopX},{@leftTopY},{@rightBottomX},{@rightBottomY}"
								href="#{@link}" alt="{@name}" />
						</xsl:for-each>
					</map>
					<xsl:for-each
						select="$job/componentList/componentItem">
						<tr>
							<td class="FONTSTYLE" align="center">
								<a href="#{@link}" class="LINKSTYLE">
									<xsl:value-of select="@name" />
								</a>
							</td>
							<td class="FONTSTYLE" align="left">
								&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
								<xsl:value-of select="@type" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
				<br />
				<!-- Components Description-->
				<!--HR-->
				<h2 class="FONTSTYLE">
					<a id="ComponentsDescription"
						name="#ComponentsDescription">
						Components Description
					</a>
				</h2>
				<xsl:for-each
					select="$job/internalNodeComponents/component">
					<a name="{@uniqueName}" />
					<table border="0" width="90%" class="FONTSTYLE"
						cellpadding="0" cellspacing="0" style="border-collapse: collapse"
						bordercolor="#111111">
						<br />
						<br />
						<tr bgcolor="#E6E6E6">
							<b class="FONTSTYLE">
								Component:&#160;&#160;
								<xsl:value-of select="componentType" />
							</b>
						</tr>
					</table>
					<BR />
					<table border="1" width="90%" class="FONTSTYLE"
						cellpadding="0" cellspacing="0" style="border-collapse: collapse"
						bordercolor="#111111">
						<tr>
							<td class="cols" align="center" rowspan="2"
								width="10%">
								<img src="{@icon}" />
								&#160;&#160;&#160;&#160;&#160;
							</td>
							<td align="center" class="TABLECOLUMNSTYLE"
								width="15%">
								UNIQUE NAME
							</td>
							<td class="FONTSTYLE" align="center"
								width="15%">
								<xsl:value-of select="@uniqueName" />
							</td>
							<td align="center" class="TABLECOLUMNSTYLE"
								width="15%">
								INPUT(S)
							</td>
							<td class="FONTSTYLE" align="center">
								<xsl:for-each select="input">
									<xsl:choose>
										<xsl:when
											test="text() = 'none'">
											<xsl:value-of
												select="text()" />
										</xsl:when>
										<xsl:otherwise>
											<a href="#{@link}"
												style="LINKSTYLE">
												<xsl:value-of
													select="text()" />
											</a>
											<xsl:if
												test="position()!= last()">
												<xsl:text>,&#160;&#160;</xsl:text>
											</xsl:if>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:for-each>
							</td>
						</tr>
						<tr>
							<td align="center"
								class="TABLECOLUMNSTYLE">
								LABEL
							</td>
							<td align="center">
								<xsl:value-of select="@label" />
							</td>
							<td align="center"
								class="TABLECOLUMNSTYLE">
								OUTPUT(S)
							</td>
							<td class="FONTSTYLE" align="center">
								<xsl:for-each select="output">
									<xsl:choose>
										<xsl:when
											test="text() = 'none'">
											<xsl:value-of
												select="text()" />
										</xsl:when>
										<xsl:otherwise>
											<a href="#{@link}"
												style="LINKSTYLE">
												<xsl:value-of
													select="text()" />
											</a>
											<xsl:if
												test="position()!= last()">
												<xsl:text>,&#160;&#160;</xsl:text>
											</xsl:if>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:for-each>
							</td>
						</tr>
					</table>
					<xsl:variable name="previewPicture"
						select="@preview" />
					<xsl:if test="string-length($previewPicture)!=0">
						<br />
						<div class="FONTSTYLE">
							<img src="{$previewPicture}"
								usemap="#jobmap" alt="No image available" class="bordercolor" />
						</div>
					</xsl:if>
					<br />
					<b class="FONTSTYLE">Component Parameters:</b>
					<br />
					<table class="cols" width="90%" border="1" cellpadding="0"
						cellspacing="0" style="border-collapse: collapse"
						bordercolor="#111111" frame="box">
						<tr>
							<th align="center" width="30%"
								class="TABLECOLUMNSTYLE">
								Properties
							</th>
							<th align="left" width="70%"
								class="TABLECOLUMNSTYLE">
								&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Values
							</th>
						</tr>
						<xsl:for-each select="parameters/column">
							<tr>
								<td class="FONTSTYLE" align="center">
									<xsl:variable name="propname"
										select="@name" />
									<xsl:value-of select="$propname" />
								</td>
								<td class="FONTSTYLE" align="left">
									&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
									<xsl:value-of select="text()" />
								</td>
							</tr>
						</xsl:for-each>
					</table>
					<br />
					<xsl:for-each select="schemas/schema">
						<b class="FONTSTYLE">
							Schema for
							<xsl:value-of select="@name" />
							:
						</b>
						<br />
						<table class="cols" width="90%" cellpadding="0"
							cellspacing="0" style="border-collapse: collapse"
							bordercolor="#111111" frame="box">
							<tr class="profont">
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Column
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Key
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Type
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Length
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Precision
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Nullable
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="16%">
									Comment
								</th>
							</tr>
							<xsl:for-each select="column">
								<tr class="FONTSTYLE">
									<td align="center">
										<xsl:value-of select="@name" />
									</td>
									<td align="center">
										<xsl:value-of select="@key" />
									</td>
									<td align="center">
										<xsl:value-of select="@type" />
									</td>
									<td align="center">
										<xsl:value-of select="@length" />
									</td>
									<td align="center">
										<xsl:value-of
											select="@precision" />
									</td>
									<td align="center">
										<xsl:value-of
											select="@nullable" />
									</td>
									<td align="center">
										<xsl:value-of select="@comment" />
									</td>
								</tr>
							</xsl:for-each>
						</table>
					</xsl:for-each>
					<br />
				</xsl:for-each>
				<xsl:for-each
					select="$job/externalNodeComponents/component">
					<a name="{@uniqueName}" />
					<table border="0" width="90%" class="FONTSTYLE"
						cellpadding="0" cellspacing="0" style="border-collapse: collapse"
						bordercolor="#111111">
						<br />
						<br />
						<tr bgcolor="#E6E6E6">
							<b class="FONTSTYLE">
								Component:&#160;&#160;
								<xsl:value-of select="componentType" />
							</b>
						</tr>
					</table>
					<BR />
					<table border="1" width="90%" class="FONTSTYLE"
						cellpadding="0" cellspacing="0" style="border-collapse: collapse"
						bordercolor="#111111">
						<tr>
							<td class="cols" align="center" rowspan="2"
								width="10%">
								<img src="{@icon}" />
								&#160;&#160;&#160;&#160;&#160;
							</td>
							<td align="center" class="TABLECOLUMNSTYLE"
								width="15%">
								UNIQUE NAME
							</td>
							<td class="FONTSTYLE" align="center"
								width="15%">
								<xsl:value-of select="@uniqueName" />
							</td>
							<td align="center" class="TABLECOLUMNSTYLE"
								width="15%">
								INPUT(S)
							</td>
							<td class="FONTSTYLE" align="center">
								<xsl:for-each select="input">
									<xsl:choose>
										<xsl:when
											test="text() = 'none'">
											<xsl:value-of
												select="text()" />
										</xsl:when>
										<xsl:otherwise>
											<a href="#{@link}"
												style="LINKSTYLE">
												<xsl:value-of
													select="text()" />
											</a>
											<xsl:if
												test="position()!= last()">
												<xsl:text>,&#160;&#160;</xsl:text>
											</xsl:if>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:for-each>
							</td>
						</tr>
						<tr>
							<td align="center"
								class="TABLECOLUMNSTYLE">
								LABEL
							</td>
							<td align="center">
								<xsl:value-of select="@label" />
							</td>
							<td align="center"
								class="TABLECOLUMNSTYLE">
								OUTPUT(S)
							</td>
							<td class="FONTSTYLE" align="center">
								<xsl:for-each select="output">
									<xsl:choose>
										<xsl:when
											test="text() = 'none'">
											<xsl:value-of
												select="text()" />
										</xsl:when>
										<xsl:otherwise>
											<a href="#{@link}"
												style="LINKSTYLE">
												<xsl:value-of
													select="text()" />
											</a>
											<xsl:if
												test="position()!= last()">
												<xsl:text>,&#160;&#160;</xsl:text>
											</xsl:if>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:for-each>
							</td>
						</tr>
					</table>
					<xsl:variable name="previewPicture"
						select="@preview" />
					<xsl:if test="string-length($previewPicture)!=0">
						<br />
						<div class="FONTSTYLE">
							<img src="{$previewPicture}"
								usemap="#jobmap" alt="No image available" class="bordercolor" />
						</div>
					</xsl:if>
					<br />
					<b class="FONTSTYLE">Component Parameters:</b>
					<br />
					<table width="90%" class="FONTSTYLE" cellpadding="0"
						cellspacing="0" style="border-collapse: collapse"
						bordercolor="#111111" frame="box">
						<tr>
							<th align="center" width="30%"
								class="TABLECOLUMNSTYLE">
								Properties
							</th>
							<th align="left" width="70%"
								class="TABLECOLUMNSTYLE">
								&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;Values
							</th>
						</tr>
						<xsl:for-each select="parameters/column">
							<tr>
								<td class="FONTSTYLE" align="center">
									<xsl:variable name="propname"
										select="@name" />
									<xsl:value-of select="$propname" />
								</td>
								<td class="FONTSTYLE" align="left">
									&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
									<xsl:value-of select="text()" />
								</td>
							</tr>
						</xsl:for-each>
					</table>
					<br />
					<xsl:for-each select="schemas/schema">
						<b class="FONTSTYLE">
							Schema for
							<xsl:value-of select="@name" />
							:
						</b>
						<br />
						<table class="cols" border="1" width="90%"
							cellpadding="0" cellspacing="0" style="border-collapse: collapse"
							bordercolor="#111111" frame="box">
							<tr class="profont">
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Column
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Key
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Type
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Length
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Precision
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="14%">
									Nullable
								</th>
								<th class="TABLECOLUMNSTYLE"
									width="16%">
									Comment
								</th>
							</tr>
							<xsl:for-each select="column">
								<tr class="FONTSTYLE">
									<td align="center">
										<xsl:value-of select="@name" />
									</td>
									<td align="center">
										<xsl:value-of select="@key" />
									</td>
									<!--  -->
									<td align="center">
										<xsl:value-of select="@type" />
									</td>
									<td align="center">
										<xsl:value-of select="@length" />
									</td>
									<td align="center">
										<xsl:value-of
											select="@precision" />
									</td>
									<td align="center">
										<xsl:value-of
											select="@nullable" />
									</td>
									<td align="center">
										<xsl:value-of select="@comment" />
									</td>
									-->
								</tr>
							</xsl:for-each>
						</table>
					</xsl:for-each>
					<br />
					<xsl:comment>
						<xsl:value-of select="@uniqueName" />ended</xsl:comment>
						<xsl:text /><!--before: $job/externalNodeComponents/component/@uniqueName -->
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
