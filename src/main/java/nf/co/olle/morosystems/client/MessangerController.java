package nf.co.olle.morosystems.client;

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
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home(Model model){
		logger.info("run home method");
		model.addAttribute("message", new Message());
		return "messages";
	}
	
	@RequestMapping(value="/messages",method=RequestMethod.GET)
	public String listOfMessages(Model model){
		logger.info("run listOfMessages method");
		model.addAttribute("message", new Message());
		return "messages";
	}
	
	@RequestMapping(value="/message/add", method=RequestMethod.POST)
	public String sendMessage(@Valid Message m,BindingResult bindingResult,Model model){
		logger.info("run sendMessage method");
		logger.info("Message's values: "+m.toString());
		model.addAttribute("message", new Message());
		logger.info(messageService.sendMessage(m));
		return "messages";
	}
}
