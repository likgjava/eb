<#list list as l>
<h3>《${l.title!}》</h3>
          <span><#if l.origin?length gt 0>--${l.origin!}。</#if>${l.author!}</span>
         <p> ${l.description!}</p>
</#list>

