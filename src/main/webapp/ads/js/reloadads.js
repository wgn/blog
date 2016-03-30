function reloadAd(txt) {
	if(txt && txt!=""){
		var run = false;
		var bottom_begin = txt.lastIndexOf("<!--ad_bottom_" + "start-->");
	    var bottom_end = txt.lastIndexOf("<!--ad_bottom_" + "end-->");
	    var ad_bot_data ="";
	    if(-1!=bottom_begin && -1!=bottom_end){
	        ad_bot_data = txt.substring(bottom_begin+22,bottom_end);
	    	reloadNewAd("bottom_ad",ad_bot_data);
	    	run = true;
	    }
	    var top_begin = txt.lastIndexOf("<!--ad_top_" + "start-->");
	    var top_end = txt.lastIndexOf("<!--ad_top_" + "end-->");
	    var ad_top_data ="";
	    if(-1!=top_begin && -1!=top_end){
	    	ad_top_data = txt.substring(top_begin+19,top_end);
	    	reloadNewAd("top_ad",ad_top_data);
	    	run = true;
	    }
	    if(run){
	    	loadScript.execute();
	    }
	}
}
function reloadNewAd(ad_container_id,ad_data){
	var new_ad_data = ad_data;
	var embed_ad_url_param = null;
	var exec_script_param = null;
	var ad_script = null;
	if(new_ad_data && new_ad_data!=""){
		var container_ad = document.getElementById(ad_container_id);
		if(!container_ad){
			return;
		}
		container_ad.innerHTML = new_ad_data;
		if(new_ad_data.indexOf("smaato") < 0 && new_ad_data.indexOf("adsbygoogle") > 0){
        	if(window.execScript) {
                window.execScript("(adsbygoogle = window.adsbygoogle || []).push({});");
            } else {
                window.eval("(adsbygoogle = window.adsbygoogle || []).push({});");
            }
        }
		if(new_ad_data.indexOf("smaato") < 0 && new_ad_data.indexOf("google_ad_client") > 0){
			ad_script = container_ad.getElementsByTagName("script");
			//console.log("google_ad_client:script:count=" + ad_script.length);
			if(ad_script && 2==ad_script.length){
				var google_ad_conf_param = ad_script[0].innerHTML;
				var google_ad_url_param = ad_script[1].src;
				if(!google_ad_conf_param || google_ad_conf_param==""){
					return;
				}
				if(!google_ad_url_param || google_ad_url_param==""){
					return;
				}
			}
			var oldIns = container_ad.getElementsByTagName("ins");
			if(oldIns && oldIns.length>0){
				for(var i = 0;i<oldIns.length;i++){
					container_ad.removeChild(oldIns[i]);
				}
			}
			loadScript.add({//google adsense  
				url : google_ad_url_param,
				container : ad_container_id,
				init : function() {
					if (window.execScript) {
						window.execScript(google_ad_conf_param);
					} else {
						window.eval(google_ad_conf_param);
					}
				},
				callback : function() {
					//console.log('');
					//there are different process what google ad at android and iphone 
					//here are for android trouble shooting
					//when device is android system,the config script will load a new scritp what named "show_ads_impl.js"
					//and it will not work right,must be reload it,
					//so we have some under code for this.
					var ad_txt = container_ad.innerHTML;
					if(ad_txt && ad_txt.indexOf("<iframe")==-1){//no iframe it's mean no ad
						var script_begin = ad_txt.lastIndexOf("<sc" + "ript");
						var script_end = ad_txt.lastIndexOf("</sc" + "ript>");
						if(script_begin!=-1 && script_end!=-1){
							var script_str = ad_txt.substring(script_begin, script_end);
							if(script_str){
								var src_start=script_str.indexOf("src=\"");
								var src_end=script_str.indexOf(".js\"");
								if(src_start!=-1 && src_end!=-1){
									var new_script_url = script_str.substring(src_start+5,src_end+3);//this part code for get new script url
									if(new_script_url){
										loadScript.add({//google adsense  
											url : new_script_url,
											container : ad_container_id,
											init : function() {
												if (window.execScript) {
													window.execScript(google_ad_conf_param);
												} else {
													window.eval(google_ad_conf_param);
												}
											},
											callback : function(){
											}
										});
									}
								}
							}
						}
					}
				}
			});
		}
		if(new_ad_data.indexOf("smaato") < 0 && new_ad_data.indexOf("requestAd") > 0){
			ad_script = container_ad.getElementsByTagName("script");
			if(ad_script && 2==ad_script.length){
				embed_ad_url_param = ad_script[0].src;
				exec_script_param = ad_script[1].innerHTML;
				if(!embed_ad_url_param || embed_ad_url_param==""){
					return;
				}
				if(!exec_script_param || exec_script_param==""){
					return;
				}
			}

			loadScript.add({
				url : embed_ad_url_param,
				container : ad_container_id,
				callback : function() {
					if(window.execScript) {
		                window.execScript(exec_script_param);
		            } else {
		                window.eval(exec_script_param);
		            }
					//console.log('');
				}
			});
		}
	}	
}