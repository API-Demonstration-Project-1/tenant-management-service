package com.toystore.ecomm.tenants.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.toystore.ecomm.ptms.daorepo.model.TenantInfo;
import com.toystore.ecomm.tenants.constants.NotificationActions;
import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.util.EmailTemplateLoader;

@Service
public class EmailService {
	
	@Value("${notification.email.defaultFromAddress}")
	private String fromAddress;
	
	@Value("${notification.email.subject.regverification}")
	private String regVerificationSubject;
	
	@Value("${notification.email.subject.regwelcome}")
	private String regWelcomeSubject;
	
	@Value("${notification.email.subject.regverificationconfirmation}")
	private String regVerificationConfirmationSubject;
	
	@Qualifier("emailTemplateEngine")
	@Autowired
    private TemplateEngine stringTemplateEngine;
	
	private static final String BACKGROUND_IMAGE = "email-templates/images/background.png";
    private static final String LOGO_BACKGROUND_IMAGE = "email-templates/images/logo-background.png";
    private static final String THYMELEAF_BANNER_IMAGE = "email-templates/images/proarchs_logo_orange_bkg.jpg";
    
    private static final String PNG_MIME = "image/png";

	private JavaMailSender javaMailSender;

	@Autowired 
	public EmailService(JavaMailSender javaMailSender) {
	  this.javaMailSender = javaMailSender; 
	}

	@Async
	public void sendNotification(TenantInfo tenantInfo, String action) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
            	Context ctx = null;
            	String bodyContent = null;
            	
            	mimeMessage.addHeader("Content-Type", "application/json");
            	
            	final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, PTMSConstants.UNICODE_ENCODING_TYPE);
            	message.setFrom(fromAddress);
            	message.setTo(tenantInfo.getTenantEmail());
            	
            	switch (action) {
	                
            		// 'Welcome' Email Notification
            		case NotificationActions.WELCOME:
            			ctx = new Context();
	                    ctx.setVariable("name", tenantInfo.getTenantName());
	                    ctx.setVariable("tenantId", tenantInfo.getTenantId());
	                    ctx.setVariable("verificationCode", tenantInfo.getTenantVerificationCode());
	                    
	                    message.setSubject(regWelcomeSubject);
	
	                    // Create the HTML body using Thymeleaf
	                    bodyContent = stringTemplateEngine.process(EmailTemplateLoader.getTemplate(PTMSConstants.WELCOME_TEMPLATE_KEY), ctx);
	                    message.setText(bodyContent, true /* isHtml */);
	                    
	                    break;
	                
	                // 'Post-Verification' Email Notification
            		case NotificationActions.POSTVERIFICATION:
            			ctx = new Context();
	                	ctx.setVariable("name", tenantInfo.getTenantName());
	                	
	                	message.setSubject(regVerificationConfirmationSubject);
	                	
	                	// Create the HTML body using Thymeleaf
	                	bodyContent = stringTemplateEngine.process(EmailTemplateLoader.getTemplate(PTMSConstants.POSTVERIFICATION_TEMPLATE_KEY), ctx);
	                    message.setText(bodyContent, true /* isHtml */);
	                    
	                    break;
            	}
            	
                // Add the inline images, referenced from the HTML code as "cid:image-name"
                message.addInline("background", new ClassPathResource(BACKGROUND_IMAGE), PNG_MIME);
                message.addInline("logo-background", new ClassPathResource(LOGO_BACKGROUND_IMAGE), PNG_MIME);
                message.addInline("thymeleaf-banner", new ClassPathResource(THYMELEAF_BANNER_IMAGE), PNG_MIME);
            }
        };
		
        // Send Mail
        javaMailSender.send(preparator);
	}

}
