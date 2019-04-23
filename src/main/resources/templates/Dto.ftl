<#if packageName?exists>package ${packageName}.dto;
</#if>
import java.util.Date;

public class ${tableName}DTO extends QkBaseDto{
    <#list fieldInfoList as field>
        <#if field.comment?exists>
            /**
            * ${field.comment}
            */
        </#if>
        private ${field.fieldTypeName} ${field.fieldName};
    </#list>
}