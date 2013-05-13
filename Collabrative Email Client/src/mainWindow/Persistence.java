package e.mail;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;

 
// the following are our persistence methods

    
class Persistence{
    
    public  Mail  openMail( Path mailPath) {
        
        String text;
        BufferedReader bufferreader = null;
        ArrayList fileContent = new ArrayList();
        
        Mail mail  = new Mail();
        
        try{
            bufferreader = new BufferedReader(new FileReader(mailPath.toString()));	
            
            while ((text = bufferreader.readLine()) != null) {
                    fileContent.add(text);
            }                          
            mail.setReivers("");
            mail.setSubject("");
            mail.setMessage("");
            mail.setpath(mailPath);
            
            // fill to text field
            text ="";
            for(int i= fileContent.indexOf("<TO>")+1;i < fileContent.indexOf("</TO>");i++) {
                text = text + fileContent.get(i).toString();
            }
            mail.setReivers(text);
            
            // fill to Subject field
            text ="";
            for(int i= fileContent.indexOf("<Subject>")+1;i < fileContent.indexOf("</Subject>");i++) {
                text = text + fileContent.get(i).toString();
            }
            mail.setSubject(text);
            
            // fill to message field
            text ="";
            for(int i= fileContent.indexOf("<Message>")+1;i < fileContent.indexOf("</Message>");i++) {

                mail.appendMessage(fileContent.get(i).toString()+"\n"); 
            }                        
            text ="";
            return mail;

        } catch (IOException e) {
                e.printStackTrace();
                return mail;
        } finally {
                try {
                        if (br != null)br.close();
                } catch (IOException ex) {
                        ex.printStackTrace();
                        return mail;
                }
        }

    }

    public  boolean saveMail(Mail mail, Path mailPath) { 
        File draft ;
        FileOutputStream outputStream = null;
        String mailContent;

        try {
                draft = new File(mailPath.toString());

                if (!draft.exists()) {
                        draft.createNewFile();
                }
                outputStream = new FileOutputStream(draft);
                
                // format file content
                 mailContent    =   "<Path>\n"      + mail.getPath().toString() +   "\n</Path>\n"
                                +   "<TO>\n"        + mail.getReivers()         +   "\n</TO>\n"
                                +   "<Subject>\n"   + mail.getSubject()         +   "\n</Subject>\n"
                                +   "<Message>\n"   + mail.getMessage()         +   "\n</Message>\n";
                
                byte[] contentInBytes = mailContent.getBytes();
                outputStream.write(contentInBytes);
                outputStream.flush();
                outputStream.close();
                
                return true;
                
        } catch (IOException except) {
                except.printStackTrace();
                System.out.println("saveButton:IOException");
              return false;
        } finally {
                try {
                        if (outputStream != null){
                                outputStream.close();
                                System.out.println("saveButton:colse");
                                return true;
                        }
                } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println("saveButton:ex");
                        return false;
                }
        }
    }
    
    public   boolean  deleteFile( Path filePath) {
        
        File file = new File(filePath.toString());
        
        //System.err.format("start:" +filePath.toString()+"\n");
        
        if (! file.exists()){
        
            System.err.format("no such file or Dirctory" +filePath.toString()+"\n");
            return false; 

        } else {
                    if(!file.isDirectory()) {

                        file.delete();
                        return true;

                    }   else {
                            if(file.list().length==0){

                                file.delete();
                                return true;
                            }   else {
                                    for(String pathTemp : file.list()){

                                        deleteFile( Paths.get(filePath.toString(),pathTemp));

                                    }
                                    file.delete();
                                        return true;
                                   
                                }
                            }
            }
    }
    
    public  boolean moveFile(Path oldPath, Path newPath) { 
        
        try {
                Files.copy(oldPath, newPath, REPLACE_EXISTING);
                return true;
        } catch (IOException except) {
                except.printStackTrace();
                return false;
        } 
    }
    
    public  boolean deleteFolder(Path path) {
      return deleteFile (path) ;  
    }
    public  boolean moveFolder(Path oldPath, Path newPath) { 
        
        try {
                Files.copy(oldPath, newPath, REPLACE_EXISTING);
                return true;
        } catch (IOException except) {
                except.printStackTrace();
                return false;
        } 
    }   
}

//this class is only for  my tests purpose, should use the class defined with the group

    
class Mail {
    String receivers;
    String subject;
    String message;
    Path  path;  
    public boolean setReivers (String to){
        this.receivers = to;
        return true;
    } 
    public boolean setSubject (String subject){
        this.subject = subject;
        return true;
    }
    public boolean setMessage (String message){
        this.message = message;
        return true;
    }
        public boolean setpath (Path path){
        this.path = path;
        return true;
    }
    public boolean appendMessage (String message){
        this.message = this.message + message;
        return true;
    }
    public String getReivers(){ return this.receivers; } 

    public String getSubject(){ return this.subject;}

    public String getMessage(){ return this.message;}

    public Path getPath(){ return this.path;}    
}