package nf.co.olle.morosystems.client.scheduler;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import nf.co.olle.morosystems.client.service.IMessageService;
import nf.co.olle.morosystems.server.Message;

/**
 * Scheduler posle novou zpravu na server pro jeji ulozeni.
 * Zprava bude obsahovat jmeno "Server" aktualni cas (ktery by mel mit 00 sekund - 15:37:00- nastaveno v XML konfiguracnim souboru)
 * @author Roman Olle
 *
 */
public class EveryMinuteScheduler extends QuartzJobBean {

	public static final Logger logger=LoggerFactory.getLogger(EveryMinuteScheduler.class);

	private IMessageService messageService;
	
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}
	
	/**
	 * Vytvori novou Message s jmenem Server a aktualnim casem, 
	 * ve zprave je info, ze se metoda spusti kazdou minutu vzdy presne v 00sekund
	 * (15:37:00)
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		Date d=new Date();
		Message message=new Message();
		message.setName("Server");
		message.setCreated(d);
		message.setMessage("It is exactly "+d.getHours()+":"+d.getMinutes());
		
		String result=messageService.sendMessage(message);
		logger.info(result);
	}

}
