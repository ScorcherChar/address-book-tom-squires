package com.squiressoftware;

import com.squiressoftware.entities.AddressBookImpl;


import java.io.IOException;
import java.net.URISyntaxException;

public class App 
{
	private static String addressDataLocation = "AddressBook";

    public static void main( String[] args )
    {

		try {
			AddressBookImpl addressBook = new AddressBookImpl(addressDataLocation);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}


    }


}
