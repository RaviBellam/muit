let maxDropdownsAllowed = 0;
let addedElemntsCounter = 0;
const usersList = [];
 
 $(document).ready(function(){ 
 	$("#addUserFormDiv").hide();
 	$("#myProfileFormDiv").hide();
 	$("#addContributionsDiv").hide();
 	$("#contibutorCntls").hide(); 
 	$("#contri_success_div").hide();
	$("#modelTrigger").hide();   -- rendering of the element
 	
 	
 	$(".addMoreBtn").click(function(){
		$("#addContributionsDiv").append(addContributorsContent); 	 
 	});
 	
 	$("#cancelContributionsBtn").on("click", function(){
		window.location.href = "/dashboard"; 	 
 	});
 	 
   
   $("#showAddUser").on("click", function(){
   	$.ajax({
     	url: "/getUsers",
     	type: "GET",     	
     	success: function(response){     	
	     	$("#resContent").html("");  		
	   		$("#dashboardControls").hide();   		
	   		$("#myProfileFormDiv").hide();
	   		$("#addUserFormDiv").hide();		
	   		$("#contibutorCntls").show();		
     	
     	 if(response.length === 0){
     	 	$("#appMsg").html("No users are registered. Please create the users to add contributions.");
     	 	$("#appMsg").addClass("alert alert-warning");     	 	
     	 }else{	
     	    response.forEach(user => {
     	 	  	usersList.push(user);
     	 	  	maxDropdownsAllowed++;     	 	
     	 	});
     	 	
			createElements(0);
     	 	$("#addContributionsDiv").show();
     	 }
     	},     	
     	error: function(errorData){
     	  	 $("#appMsg").html("Something went wrong while fetching contributors, Please try after sometime");
	     	 $("#appMsg").addClass("alert alert-danger");
     	}     
     }); 	
     
   });
   
   
   $("#createUserItem").on("click", function(){
   		$("#resContent").html("");   		
   		$("#dashboardControls").hide();   		
   		$("#myProfileFormDiv").hide();
   		$("#addContributionsDiv").hide();   		
   		$("#contibutorCntls").hide();   		
   		$("#addUserFormDiv").show();   		
   });
   
   
   $("#createUser").on("click", function(event){
   		event.preventDefault();		
		const formData = $("#createUserForm");
		const userObj = {
		  name: $("#name").val(),
		  emailId: $("#userEmail").val(),
		  mobileNo: $("#mobileNo").val()		  
		};		
				
		$.ajax({
			type: "POST",
			url: "/createUser",		    
      		contentType: "application/json",      		
    		data: JSON.stringify(userObj),    
			success: function(response){				
				$("#appMsg").html("User <b>"+response.name + "</b> created successfully and a link was sent to <b><u>" + response.email + "</u></b> to activate the details, Please check the mail box.");
     	 		$("#appMsg").addClass("alert alert-success");
			},
			error: function(err){
				alert(err);
			}		
		});   	
   });
   
   
   $("#myProfile").on("click", function(){
   		$("#resContent").html("");   		
   		$("#addUserFormDiv").hide();
   		$("#dashboardControls").hide();    		
   		
   		$.ajax({
   		 url: "/profile",
   		 type: "GET", 
   		 success: function(response){   		 
   		 	$("#p_name").val(response.name);
   		 	$("#p_email").val(response.emailId);
   		 	$("#p_mobile").val(response.mobileNo);   		 	
   		 },
   		 
   		 error: function(err){
   		 	alert("error");
   		 }   		
   		});   		
   		   		
   		$("#myProfileFormDiv").show();   		
   });
   
   
   $("#signout").on("click", function(){
   		alert("about to log out from the application");   		
   });    
    
   $("#startDate").on("change", function(event){
     $.ajax({
     	url: "/loadContributors?startDate=" + event.target.value,
     	type: "GET",     	
     	success: function(response){     		
     		const tableStartTag= "<table class='table table-hover'>";
     		const tableEndTag= "</table>";
     		const trStartTag= "<tr>";
     		const trEndTag= "</tr>";
     		const tdStartTag= "<td>";
     		const tdEndTag= "</td>";     		
     		
     		let header = tableStartTag + trStartTag + tdStartTag + "S.No" + tdEndTag;
     		header += tdStartTag + "Name" + tdEndTag;
     		header += tdStartTag + "Amount" + tdEndTag;
     		header +=  trEndTag;
     		
     		let finalTableData = header;
			
     		response.forEach((contData, index) => {
     		
			let tableData = '';
     		if(contData.name === 'Totals'){
     			tableData = trStartTag + tdStartTag + tdEndTag;
     		}else{
     		 	tableData = trStartTag + tdStartTag + (index + 1) + tdEndTag;
     		}
				tableData += tdStartTag + contData.name + tdEndTag;
				tableData += tdStartTag + contData.amount + tdEndTag;
				tableData +=  trEndTag;				
				finalTableData += tableData;     		  
     		});
			
			$("#resContent").html(finalTableData + tableEndTag);
     	  
     	},
     	
     	error: function(errorData){
     	  alert("failed" + errorData);
     	}
     });
   }); 
   
   $("#saveContributorsId").on("click", function(){
	  
		let index = 0;
		const requestObj = [];
		let isDataValid = true;
		while(index < addedElemntsCounter){
			const selectElmnt = document.getElementById(index);
			const selectedOpt = selectElmnt.options[selectElmnt.selectedIndex];
			const selectedContributorName = selectedOpt.text;			
			const contributionElmnt = document.getElementById("cnt_" + index);
			
			if(selectedContributorName === "Select contributor"){
				selectElmnt.classList.add("errBorder");
				selectElmnt.focus();
				isDataValid = false;
				break;		
				
			} else if(contributionElmnt.value <= 0){
				selectElmnt.classList.remove("errBorder");
				contributionElmnt.classList.add("errBorder");				
				isDataValid = false;
				contributionElmnt.focus();
				break;
			}else{
				contributionElmnt.classList.remove("errBorder");
				selectElmnt.classList.remove("errBorder");
			}			
			
			const contObj = {id:selectedOpt.value, name: selectedContributorName, amount: contributionElmnt.value};			
			
			requestObj.push(contObj);
			index++;
		}
		
		if(isDataValid){
			$.ajax({
				type: "POST",
				url: "/saveContributions",			    
      			contentType: "application/json",       			  
				data: JSON.stringify(requestObj),
				success: function(response){
					//$("#modelTrigger").click();
				
				$("#contri_success_div").dialog({
						resizable: false,
						height: "auto",
						width: 400,
						modal: true,
						buttons: {
							"Go to Dashboard": function(){
								$(this).dialog("close");
								window.location.href = "/dashboard";								
							},
							"Add More": function(){
								addedElemntsCounter = 0;
								usersList.splice(0, usersList.length -1);
								$("#addContributionsDiv").html('');								
								$(this).dialog("close");
								$("#showAddUser").click();
							}							
						},
						close: function(){
							
						}	
					}).addClass("modal-dialog"); 
					
				},
				error: function(err){
					alert("Something went wrong..! Please try again. " + err);
				}
				
			});	
		}
	   
   });
   
  
 }); 
 
function createElements(id){
		const newRowDivElmnt = document.createElement("div");     	 	
		const selectElmnt = document.createElement("select");
			  selectElmnt.setAttribute("id", addedElemntsCounter);
			  selectElmnt.classList.add("col-3");
			  selectElmnt.setAttribute("onChange", "validateContributor(" + addedElemntsCounter +")");
	    const optionElement = document.createElement("option");
		optionElement.text = "Select contributor";
		
		selectElmnt.appendChild(optionElement);
		usersList.forEach(user =>{
			const optionElement = document.createElement("option");
			optionElement.text = user.name;
			optionElement.value = user.id;
			selectElmnt.appendChild(optionElement);
		});
		
		const divElmnt = document.createElement("div");
		divElmnt.classList.add("col-3");
		newRowDivElmnt.appendChild(divElmnt);
		
		//add select to the parent     	 	
		newRowDivElmnt.appendChild(selectElmnt);
		newRowDivElmnt.classList.add("row", "row-padding");
		
		//2. create amount field
		const amountElmnt = document.createElement("input");
			amountElmnt.setAttribute("id", "cnt_" + addedElemntsCounter);
			amountElmnt.setAttribute("type", "number");     	 	
			amountElmnt.setAttribute("required", true);
			amountElmnt.classList.add("col-3");
		newRowDivElmnt.appendChild(amountElmnt);
		
		//3. create button element
		const addBtnElmnt = document.createElement("button");
			addBtnElmnt.setAttribute("id", "btn_" + addedElemntsCounter);
			addBtnElmnt.setAttribute("onClick", "addMoreUsers("+addedElemntsCounter +")");
			addBtnElmnt.innerHTML = "&#43;";
			addBtnElmnt.classList.add("col-1", "btn", "btn-primary");
		newRowDivElmnt.appendChild(addBtnElmnt);		
		const parentDiv = document.getElementById("addContributionsDiv");
		parentDiv.appendChild(newRowDivElmnt);
		addedElemntsCounter++;
}
 
function addMoreUsers(id){
	if((maxDropdownsAllowed -1)  > 0){    
	
	  let index = 0;
	  let areAllFeildsFilled = true;
	  
	  while(areAllFeildsFilled && index < addedElemntsCounter){
	   const clickedIndex = document.getElementById(index);
	   const contributorName = clickedIndex.options[clickedIndex.selectedIndex].text;	   
	   const enteredAmt = document.getElementById("cnt_"+index).value;
	 
		if(contributorName === "Select contributor" || enteredAmt <= 0){
			areAllFeildsFilled  = false;
		}		
	 	index++;
	  }
	  
	  if(areAllFeildsFilled){
		  createElements(index);
		  maxDropdownsAllowed--;
	  }
	 
	}
}

function validateContributor(id){
	
	const selectDropdown1 = document.getElementById(id);
	const selectedOption = selectDropdown1.options[selectDropdown1.selectedIndex].text;
			
	for(let index = 0; index < addedElemntsCounter; index++){		
		if(index !== id){
			const selectDropdown = document.getElementById(index);
			const optionText = selectDropdown.options[selectDropdown.selectedIndex].text;
			if(selectedOption === optionText){
				alert("Contributor "+ selectedOption + " is already selected");
				selectDropdown1.selectedIndex = 0;				
				break;
			}			
		}			
	}
}
