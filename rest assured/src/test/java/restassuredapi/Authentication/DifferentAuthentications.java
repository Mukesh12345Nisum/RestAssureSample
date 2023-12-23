package restassuredapi.Authentication;

import org.testng.annotations.Test;

import java.util.Base64;

public class DifferentAuthentications
{
  /*
   Authentication - who you are
                    provides you the identity
                    getting driving license

   Authorisation - what you can do
                   provides you right to access
                   only access to drive two wheeler
   */

   @Test
    public void basic()
   {
       String uspw="MyUsername:MyPassword";

       //Encoding
       String base64Encode= Base64.getEncoder().encodeToString(uspw.getBytes());
       System.out.println("Encoded : " + base64Encode);
        //Decoding
       byte[] decoded= Base64.getDecoder().decode(base64Encode);
       System.out.println("Decoded : " + new String(decoded));

   }


}
