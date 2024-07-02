package teamx.musiccollabbackend.domain;

import com.jcraft.jsch.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class SFTPTest {

//    private String remoteHost = "theownage.stack.storage";
//    private String username = "theownage@theownage.stack.storage";
//    private String password = "BIvGSlN3um_XVuZy-95xHuWned8";
//
//    private ChannelSftp setupJsch() throws JSchException {
//
//        JSch jsch = new JSch();
//        jsch.setKnownHosts("/Users/john/.ssh/known_hosts");
//        Session jschSession = jsch.getSession(username, remoteHost);
//        java.util.Properties config = new java.util.Properties();
//        config.put("StrictHostKeyChecking", "no");
//        jschSession.setConfig(config);
//        jschSession.setPassword(password);
//        jschSession.connect();
//        return (ChannelSftp) jschSession.openChannel("sftp");
//    }
//
//    @Test
//    public void whenUploadThenSuccess() throws JSchException, SftpException {
//        ChannelSftp channelSftp = setupJsch();
//        channelSftp.connect();
//
//        String localFile = "src/main/resources/sample.txt";
//        String remoteDir = "samples/";
//        String newSampleName = "";
//        channelSftp.put(localFile, remoteDir + "jschFile.txt");
//
//        channelSftp.exit();
//    }
//
//    @Test
//    public void whenDownloadThenSuccess() throws JSchException, SftpException {
//        ChannelSftp channelSftp = setupJsch();
//        channelSftp.connect();
//
//        String remoteFile = "samples/welcome.txt";
//        String localDir = "src/main/resources/";
//
//        channelSftp.get(remoteFile, localDir + "jschFile.txt");
//
//        channelSftp.exit();
//    }
//
//    //  Get selected sample for user from frontend
//    //  Retrieve sample data from database
//    //  Retrieve actual sample from sftp server
//    @Test
//    public void whenSampleSelectedFromDatabaseThenRetrieveSampleFromSFTPServer() throws JSchException, SftpException, SQLException {
//
//    }
//
//
//    //Retrieve sample upload data from frontend
//    //Push sample data to database with unique path
//    //Upload actual sample to sftp server
//    @Test
//    public void whenUserUploadSampleDatabaseRegisterInsertSampleData() throws JSchException, SftpException {
//
//    }
}
