input {  
  tcp {  
    port => ${port}   #读取TCP端口  
    mode => server
    codec => json_lines {charset => ["UTF-8"]}
  }  
}
filter{
<#list items as item>
	  <#if item.alertType == "1">
	  if [ keyword ] == "${item.keyword}" {
	      mutate{
	        add_field => {"alertLevel" => "${item.alertLevel}"} # 添加字段
	      }
	  }
	  <#elseif item.alertType == "2">
	  if [ keyword ] == "${item.keyword}" { 
	      grok {
	        match => ["message", ".*There is/are\s(?<threshold>\d*)\sthread\(s\).*"]
	      }
	      mutate {
	        convert => ["threshold", "float"]
	      }
	      if [ threshold ] >= ${item.lowerThreshold} {
		      mutate{
		        add_field => {"alertLevel" => "lower"} # 添加字段
		      }
	      }
	      if [ threshold ] > ${item.lowerThreshold} and [ threshold ] <= ${item.highThreshold}{
		      mutate{
		        add_field => {"alertLevel" => "mid"} # 添加字段
		      }
	      }
	      if [ threshold ] > ${item.highThreshold} {
		      mutate{
		        add_field => {"alertLevel" => "high"} # 添加字段
		      }
	      }
	  }
	  </#if>
	  mutate{
	    add_field => [ "received_at", "%{@timestamp}" ] #添加字段
	    add_field => [ "received_from", "%{host}" ]     #添加字段  
	  }
</#list>
}
output {
    #elasticsearch {
    #    host => 'localhost'
    #    protocol => "http"
    #}
　　#把采集的数据输出到elasticsearch里面。
    stdout {
        codec => rubydebug
    }
　　#输出到屏幕上
}