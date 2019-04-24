<#if packageName?exists>package ${packageName}.dto;
</#if>
import java.util.Date;

public class ${className}DTO extends QkBaseDto{
<#list fieldInfoList as field>
    <#if field.comment!=''>
        /**
        * ${field.comment}
        */
    </#if>
    private ${field.fieldTypeName} ${field.fieldName};
</#list>
<#list methodInfoList as method>
    <#if method.comment?exists>
        /**
        * ${method.comment}
        */
    </#if>
    ${method.accessibility} ${method.methodTypeName} ${method.methodName}(${method.argumentNames} ){
        ${method.methodBody}
    }
</#list>

}