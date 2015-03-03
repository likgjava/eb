package com.gpcsoft.epp.webService.handlers.log;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;

import com.gpcsoft.core.utils.DateUtil;
public class LogHandler extends BasicHandler {

	public void invoke(MessageContext messageContext) throws AxisFault {
		//每当web服务被调用，都记录到log中。
		try {
			Handler handler = messageContext.getService();
			String filename = (String) getOption("filename");
			if ((filename == null) || (filename.equals("")))
				throw new AxisFault("Server.NoLogFile","No log file configured for the LogHandler!", null,null);
			FileOutputStream fos = new FileOutputStream(filename, true);
			PrintWriter writer = new PrintWriter(fos);
			Integer counter = (Integer) handler.getOption("accesses");
			if (counter == null)
				counter = new Integer(0);
			counter = new Integer(counter.intValue() + 1);
			handler.setOption("accesses", counter);
			writer.println("在".concat(DateUtil.format(new Date(), "yyyy-MM-dd HH:ss")).concat(": Web 服务 ").concat(messageContext.getTargetService()).concat(" 被调用，现在已经共调用了 "	).concat(counter.toString()).concat(" 次."));
			writer.flush();
			writer.println("请求信息:");
			writer.flush();
			messageContext.getMessage().writeTo(fos);
			writer.println();
			writer.flush();
			writer.close();
		} catch (Exception e) {
			throw AxisFault.makeFault(e);
		}
	}

}
