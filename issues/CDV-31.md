---

title: "Failed to share Hibernate lazy-fetched object "
layout: issue
tags: 
permalink: /browse/CDV-31

issue_key: CDV-31
issue_numeric_sort_key: 31
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Open"
resolution: ""
priority: "3 Minor"
components: "DSO:L1"
labels: 
assignee: "asi"
reporter: "lyi"
votes:  1
watchers: 1

created: "2006-11-08T16:19:12.000-0500"
updated: "2006-12-12T18:37:24.000-0500"
resolved: ""

---



{% raw %}



## Description

<div markdown="1" class="description">

13:16:37,312  WARN RemoteInvocationTraceInterceptor:80 - Processing of HttpInvokerServiceExporter remote call resulted in fatal exception: com.tctest.spring.bean.IHibernateBean.shareObjWithLazyChild
java.lang.AssertionError: com.tctest.spring.bean.domain.PersistentObj$$EnhancerByCGLIB$$9303dc72.CGLIB$CALLBACK\10 does not exist in map returned from \1\1tc\1getallfields. Class is com.tctest.spring.bean.domain.PersistentObj$$EnhancerByCGLIB$$9303dc72. field Values = {com.tctest.spring.bean.domain.PersistentObj.child=null, com.tctest.spring.bean.domain.PersistentObj.strFld=lazy, com.tctest.spring.bean.domain.PersistentObj.id=3}
	at com.tc.object.applicator.PhysicalApplicator.dehydrate(PhysicalApplicator.java:134)
	at com.tc.object.TCClassImpl.dehydrate(TCClassImpl.java:160)
	at com.tc.object.TCObjectImpl.dehydrate(TCObjectImpl.java:283)
	at com.tc.object.change.TCChangeBufferImpl.writeTo(TCChangeBufferImpl.java:93)
	at com.tc.object.tx.TransactionBatchWriter.addTransaction(TransactionBatchWriter.java:130)
	at com.tc.object.tx.RemoteTransactionManagerImpl.commit(RemoteTransactionManagerImpl.java:221)
	at com.tc.object.tx.ClientTransactionManagerImpl.commit(ClientTransactionManagerImpl.java:382)
	at com.tc.object.tx.ClientTransactionManagerImpl.commit(ClientTransactionManagerImpl.java:307)
	at com.tc.object.bytecode.ManagerImpl.monitorExit(ManagerImpl.java:501)
	at com.tc.object.bytecode.ManagerUtil.monitorExit(ManagerUtil.java:199)
	at com.tctest.spring.bean.HibernateBean.setSharedOne(HibernateBean.java)
	at com.tctest.spring.bean.HibernateBean.shareObjWithLazyChild(HibernateBean.java:60)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:585)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:318)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:203)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:162)
	at org.springframework.remoting.support.RemoteInvocationTraceInterceptor.invoke(RemoteInvocationTraceInterceptor.java:70)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:209)
	at $Proxy1.shareObjWithLazyChild(Unknown Source)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:585)
	at org.springframework.remoting.support.RemoteInvocation.invoke(RemoteInvocation.java:181)
	at org.springframework.remoting.support.DefaultRemoteInvocationExecutor.invoke(DefaultRemoteInvocationExecutor.java:38)
	at org.springframework.remoting.support.RemoteInvocationBasedExporter.invoke(RemoteInvocationBasedExporter.java:76)
	at org.springframework.remoting.support.RemoteInvocationBasedExporter.invokeAndCreateResult(RemoteInvocationBasedExporter.java:112)
	at org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter.handleRequest(HttpInvokerServiceExporter.java:117)
	at org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter.handle(HttpRequestHandlerAdapter.java:47)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:806)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:736)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:396)
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:360)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:768)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:861)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:252)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:173)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:213)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:178)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:126)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:105)
	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:541)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:107)
	at com.tc.tomcat55.session.SessionValve55.invoke(SessionValve55.java:42)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:148)
	at org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:869)
	at org.apache.coyote.http11.Http11BaseProtocol$Http11ConnectionHandler.processConnection(Http11BaseProtocol.java:664)
	at org.apache.tomcat.util.net.PoolTcpEndpoint.processSocket(PoolTcpEndpoint.java:527)
	at org.apache.tomcat.util.net.LeaderFollowerWorkerThread.runIt(LeaderFollowerWorkerThread.java:80)
	at org.apache.tomcat.util.threads.ThreadPool$ControlRunnable.run(ThreadPool.java:684)
	at java.lang.Thread.run(Thread.java:595)
Hibernate: insert into POBJ (STR\1FLD, CHILD\1ID, OBJ\1ID) values (?, ?, null)
Hibernate: call identity()
java.lang.AssertionError: Attempt to set sequence id more than once.
	at com.tc.object.tx.AbstractClientTransaction.setSequenceID(AbstractClientTransaction.java:29)
	at com.tc.object.tx.RemoteTransactionManagerImpl.commit(RemoteTransactionManagerImpl.java:212)
	at com.tc.object.tx.ClientTransactionManagerImpl.commit(ClientTransactionManagerImpl.java:382)
	at com.tc.object.tx.ClientTransactionManagerImpl.commit(ClientTransactionManagerImpl.java:307)
	at com.tc.object.bytecode.ManagerImpl.monitorExit(ManagerImpl.java:501)
	at com.tc.object.bytecode.ManagerUtil.monitorExit(ManagerUtil.java:199)
	at com.tctest.spring.bean.HibernateBean.setSharedOne(HibernateBean.java)
	at com.tctest.spring.bean.HibernateBean.sharePersistentObj(HibernateBean.java:24)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:585)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:318)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:203)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:162)
	at org.springframework.remoting.support.RemoteInvocationTraceInterceptor.invoke(RemoteInvocationTraceInterceptor.java:70)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:209)
	at $Proxy1.sharePersistentObj(Unknown Source)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:585)
	at org.springframework.remoting.support.RemoteInvocation.invoke(RemoteInvocation.java:181)
	at org.springframework.remoting.support.DefaultRemoteInvocationExecutor.invoke(DefaultRemoteInvocationExecutor.java:38)
	at org.springframework.remoting.support.RemoteInvocationBasedExporter.invoke(RemoteInvocationBasedExporter.java:76)
	at org.springframework.remoting.support.RemoteInvocationBasedExporter.invokeAndCreateResult(RemoteInvocationBasedExporter.java:112)
	at org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter.handleRequest(HttpInvokerServiceExporter.java:117)
	at org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter.handle(HttpRequestHandlerAdapter.java:47)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:806)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:736)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:396)
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:360)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:768)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:861)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:252)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:173)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:213)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:178)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:126)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:105)
	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:541)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:107)
	at com.tc.tomcat55.session.SessionValve55.invoke(SessionValve55.java:42)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:148)
	at org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:869)
	at org.apache.coyote.http11.Http11BaseProtocol$Http11ConnectionHandler.processConnection(Http11BaseProtocol.java:664)
	at org.apache.tomcat.util.net.PoolTcpEndpoint.processSocket(PoolTcpEndpoint.java:527)
	at org.apache.tomcat.util.net.LeaderFollowerWorkerThread.runIt(LeaderFollowerWorkerThread.java:80)
	at org.apache.tomcat.util.threads.ThreadPool$ControlRunnable.run(ThreadPool.java:684)
	at java.lang.Thread.run(Thread.java:595)

</div>

## Comments


{:.comment-heading}
### **Tim Eck** <span class="date">2006-11-08</span>

<div markdown="1" class="comment">

It's hard to see, but it looks like there are two DSO related exceptions in there:

#1) A field value was not returned in the map passed to TransparentAccess.\1\1tc\1getAllFields() on this class. Most likely this is because the field name has a "$" character in it. 

#2) We try to commit again since we throw an exception in the first commit, triggering the assertion about setting the TXN more than once



</div>


{:.comment-heading}
### **Liyu Yi** <span class="date">2006-11-08</span>

<div markdown="1" class="comment">

Thansk for the comment. 

The test reproduce the failure will be in HibernateTest.java

I know you folks are still working on supporting sharing cglib proxied object. I timebombed the failed ones in the above test.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2006-11-09</span>

<div markdown="1" class="comment">

Saravanan, is this required for Lawton/shasta timeframe?

</div>


{:.comment-heading}
### **Liyu Yi** <span class="date">2006-11-09</span>

<div markdown="1" class="comment">

Antonio and Liyu will work together on this issue

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2006-11-22</span>

<div markdown="1" class="comment">

What's the status of this? Should I push it out?

</div>


{:.comment-heading}
### **Antonio Si** <span class="date">2006-11-22</span>

<div markdown="1" class="comment">

Liyu and I looked at it a couple weeks ago. The instrumentation of the generated class from cglib is quite messed up. I need more information regarding how this class is generated from cglib to find out where the instrumentation fail. Liyu will look into that once he finishes his current task.

</div>


{:.comment-heading}
### **Antonio Si** <span class="date">2006-11-29</span>

<div markdown="1" class="comment">

CGLIB gets the declared methods from the supplied class and uses the method definitions to generate the methods for the proxy subclass. Since the supplied class is instrumented, it has the \1\1tc\1geallfields() method defined. The CGLIB thus create a \1\1tc\1getallfields() method for the generated subclass as well. When the subclass goes through the dso instrumentation, the adapter sees this CGLIB generated \1\1tc\1getallfields() method and will not generate a TC version of \1\1tc\1getallfields() method. That's why when the PhysicalApplicator tries to dehydrate an object of this generated subclass, the map returned from a call to \1\1tc\1getallfields() does not contain the fields defined.

There could be 2 possible solutions. First is to intercept CGLIB and filter out all tc methods after CGLIB gets all methods from the superclass. The second solution is to filter out all tc methods from class.getDeclaredMethods().

We try the first solution and it solves this problem, but the test still fails because the fields CGLIB generates for the subclass has an indirect
reference to type java.lang.ref.Method which is currently not supported. There is another jira task to support that (LKC-2831). So, until that is fix, this test will continue not working.



</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2006-11-29</span>

<div markdown="1" class="comment">

I just wonder if we can replace those \1\1tc\1getallfields() generated by CGLIB? I.e. just recreate them. Then it will probably less specific to CGLIB.

</div>



{% endraw %}
