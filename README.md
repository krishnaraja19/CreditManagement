# CreditManagement

1. After Springboot starts application event listner call to BRIXO API and return the data as JSON Object.

2. Converting JSON Object into Application object by use of Object mapper.

3. Scheduler runs every one minute and get the valid invoice detail from H2 Database. DB access implemented by JPA.

4. After getting invoice data, a PDF generator called and save the invoice file as per a single customer.

5. Once the PDF invoice generate it will send the pdf file to The email sender. It will deliver the email as per the customer email id.

5. I have used the spring-boot-jasypt-starter jar file for password encryption. We need to give the below parameter as VM arguments. Then we can use this feature.

-Djasypt.encryptor.password=tiger21

6. I have maintained all email validation related properties in the application.properties. So if any changes come from the Product Owner, we can change very easily.

7. I have added the validation.java file for validating the mail properties. It will provide good quality applications.

8. I have used Spring boot exception handling features. The below annotations provide the help to handle the exceptions @ControllerAdvice and @ExceptionHandler.

9. I have provided two REST endpoint, It will give all application details and all plan details.

