package com.git.gestor_academico.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("gestoracademico28@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String templateHtml() {
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
                    }
                    .header {
                      background-color: #007bff;
                      color: #ffffff;
                      padding: 20px;
                      text-align: center;
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
                    <h1>Bem-vindo(a)!</h1>
                  </div>
                                
                  <!-- Conteúdo -->
                  <div class="content">
                    <h2>Olá, [Nome do destinatário]</h2>
                    <p>Estamos muito felizes em tê-lo(a) conosco! Esta é uma breve mensagem de boas-vindas para agradecer por sua confiança e para dizer que estamos aqui para ajudá-lo(a) a alcançar seus objetivos.</p>
                    <p>Se você tiver alguma dúvida ou precisar de qualquer suporte, por favor, entre em contato conosco. Estamos prontos para ajudar!</p>
                    <a href="[Link de Ação]" class="button">Comece Agora</a>
                  </div>
                                
                  <!-- Rodapé -->
                  <div class="footer">
                    <p>© 2024 Sua Empresa. Todos os direitos reservados.</p>
                    <p>Endereço da empresa | Telefone | Email</p>
                  </div>
                </div>
                                
                </body>
                </html>
                                
                """;
    }

}
