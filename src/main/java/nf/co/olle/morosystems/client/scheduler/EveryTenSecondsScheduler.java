package nf.co.olle.morosystems.client.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import nf.co.olle.morosystems.client.service.IMessageService;

/**
 * Scheduler, ktery ulozi do logu pocet radku z databaze tabuly Message
 * (V XML konfiguracnim souboru je nastaveno, ze se po spusteni aplikace ma spoustet kazdych 10 sekund)
 * @author Roman Olle
 *
 */
public class EveryTenSecondsScheduler extends QuartzJobBean {
 
	public static final Logger logger=LoggerFactory.getLogger(EveryTenSecondsScheduler.class);

	private IMessageService messageService;
	
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}
	
	/**
	 * Pozada server o pocet zaznamu z tabulky Message
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		Long rowCount=messageService.getRowCount();
		logger.info("Every ten seconds scheduler - number of messages in databse: "+rowCount);
	}
}
