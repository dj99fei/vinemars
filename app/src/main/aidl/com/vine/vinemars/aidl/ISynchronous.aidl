package com.vine.vinemars.aidl;
interface ISynchronous {
	String getThreadNameFast();
	String getThreadNameSlow(long sleep);
   	String getThreadNameBlocking(); 
	String getThreadNameUnblock();
}
