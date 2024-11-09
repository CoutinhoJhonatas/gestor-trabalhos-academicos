package com.git.gestor_academico.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    //TODO tornar os envios de emails assincrono, pois da forma que está demora para retornar as requisições
    public void sendEmail(String to, String subject, String text) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("gestoracademico28@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(templateHtml(text), true);
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String templateHtml(String mensagemEmail) {
        return """
                <!DOCTYPE html>
                   <html lang="pt-BR">
                   <head>
                     <meta charset="UTF-8">
                     <meta name="viewport" content="width=device-width, initial-scale=1.0">
                     <title>Email Template</title>
                     <style>
                       body {
                         font-family: Arial, sans-serif;
                         margin: 0;
                         padding: 0;
                         background-color: #f4f4f4;
                       }
                       .container {
                         width: 100%;
                         max-width: 600px;
                         margin: 0 auto;
                         background-color: #ffffff;
                         border-radius: 8px;
                         overflow: hidden;
                         box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                         text-align: center;
                       }
                       .header {
                         background-color: #007bff;
                         color: #ffffff;
                         padding: 20px;
                       }
                       .header h1 {
                         margin: 0;
                       }
                       .content {
                         padding: 20px;
                         color: #333333;
                         line-height: 1.6;
                       }
                       .content h2 {
                         color: #007bff;
                       }
                       .button {
                         display: inline-block;
                         background-color: #007bff;
                         color: #ffffff;
                         padding: 10px 20px;
                         text-decoration: none;
                         border-radius: 5px;
                         margin-top: 20px;
                       }
                       .footer {
                         background-color: #f4f4f4;
                         color: #777777;
                         text-align: center;
                         padding: 10px;
                         font-size: 12px;
                       }
                     </style>
                   </head>
                   <body>
            
                   <div class="container">
                     <!-- Cabeçalho -->
                     <div class="header">
                       <h1>Aviso importante!</h1>
                     </div>
            
                     <!-- Conteúdo -->
                     <div class="content">
                       <h2>Olá</h2>
                       <p><b>
                """
                + mensagemEmail +
                """ 
                       </b></p>
                       <p>Por favor, entrar na plataforma e verificar!</p>
                     </div>
            
                     <!-- Rodapé -->
                     <div class="footer">
                       <p>© 2024 Centro Universitário Eniac</p>
                       <p>Todos os direitos reservados</p>
                     </div>
                   </div>
            
                   </body>
                   </html>
                """;
    }

}
