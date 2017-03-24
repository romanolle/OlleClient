package nf.co.olle.morosystems.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nf.co.olle.morosystems.client.service.IMessageService;
import nf.co.olle.morosystems.server.Message;

/**
 * Prijima zpravy od uzivatele, ktere si uklada, kazdou prijatou zpravu odesle pres Service Layer na ulozeni do databaze.
 * Vsechny metoda vraci zpatky stranku messages.jsp + do modelu pridavaji seznam uzivatelu.
 * @author Roman Olle
 *
 */
@Controller
public class MessangerController {

	public static final Logger logger=LoggerFactory.getLogger(MessangerController.class);
	
	public static List<Message> messages;
	
	private IMessageService messageService;
	
	@Autowired
	@Qualifier("messageService")
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}
	
	/**
	 * Default page=>pridani do modelu list zprav a prazdnou instanci zpravy pro formular.
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home(Model model){
		logger.info("run home method");
		model.addAttribute("message", new Message());
		model.addAttribute("messages", messages);
		return "messages";
	}
	
	/**
	 * Shodne s default page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/messages",method=RequestMethod.GET)
	public String listOfMessages(Model model){
		logger.info("run listOfMessages method");
		model.addAttribute("message", new Message());
		model.addAttribute("messages", messages);
		return "messages";
	}
	
	/**
	 * URL pro odeslani zpravy, ktera se posle do databaze pres service layer.
	 * @param m Zprava
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/message/add", method=RequestMethod.POST)
	public String sendMessage(@Valid Message m,BindingResult bindingResult,Model model){
		logger.info("run sendMessage method");
		
		if(bindingResult.hasErrors()){
			logger.info("sendMessage method - bad values");
			model.addAttribute("messages", messages);
			return "messages";
		}
		
		//pridani data vytvoreni
		m.setCreated(new Date());
		logger.info("Message's values: "+m.toString());

		if(messages==null)
			messages=new ArrayList<Message>();
		messages.add(m);
		
		model.addAttribute("message", new Message());
		model.addAttribute("messages", messages);
		
		
		
		//odeslani nove zpravy pres Service Layer do databaze
		String result=messageService.sendMessage(m);
		model.addAttribute("result", result);
		logger.info(result);
		return "messages";
	}
}
