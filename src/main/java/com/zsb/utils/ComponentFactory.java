package com.zsb.utils;

import com.zsb.constansts.Consts;
import com.zsb.model.IComponent;
import com.zsb.model.LbLbLbComponent;
import com.zsb.model.TfTfBtComponent;


/**
 * Date: 2016年5月8日 <br>
 * @author zhoushanbin
 */
public class ComponentFactory {
		
	private static ComponentFactory instance;
	
	private ComponentFactory(){
		
	}
	
	public static ComponentFactory getInstance(){
		
		if(null != instance){
			return instance;
		}
		synchronized (ComponentFactory.class) {
			if(null != instance){
				return instance;
			}
			instance = new ComponentFactory();
		}
		return instance;
	}
	
	public IComponent getComponent(String type){
		if(Consts.CompConst.TFTFBT.equals(type)){
			return new TfTfBtComponent();
		}
		if(Consts.CompConst.LBLBLB.equals(type)){
			return new LbLbLbComponent();
		}
		return null;
	}
	
}
