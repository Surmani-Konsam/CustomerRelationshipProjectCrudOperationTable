/**
 * 
 */

 
 function addRow(tableID){
	 console.log(tableID);
	 //alert("Table is fetched in the console!!!")
	 //so id of the table is fetched in the table.
	 
	 //doing this it will help me create an object for the table with id 'tableID'.
	  var table = document.getElementById(tableID); 
	  console.log(table);
	  
	  var rowCount = table.rows.length;
	  console.log(rowCount);
	  
	   
	 //2. Insert row at the row index or the row length. done
	 var row = table.insertRow(rowCount);
	 //since we have four cells, i.e serialNumber, firstName, lastName, emailId and actionButton with two buttons in-it.
	 //They are update button and delete button.
	 
	 var cell1 = row.insertCell(0);
	 var cell2 = row.insertCell(1);
	 var cell3 = row.insertCell(2);
	 var cell4 = row.insertCell(3);
	 var cell5 = row.insertCell(4); //where 4 represents the 5th cell.
	 
	 cell1.innerHTML = rowCount;
	 cell2.innerHTML = "FirstName";
	 cell3.innerHTML = "LastName";
	 cell4.innerHTML = "Email_Id";
	 //cells 0,1,2 and 3 represents the index of the cell in analogy with the array index.
	 //3. Insert the cells in the row inserted using insertCell(args). done.
	 
	 //now time to add content in the innnerhtml.
	 //so the very advantage of innerHtml is that we can use it to add any element inside the cell thus created.
	 cell5.innerHTML = '<button style="width:100px; font-weight:bold onclick="updateDetails()">Update</button> <span style="margin-left:20px; font-weight: bold"><button style="width:100px; font-weight: bold" onclick="deleteInformation()">Delete</button></span>';
	 
	 
	 //we have inserted our row here, in the 
}
 
 
function showDetails(){
	var name=document.getElementById("first").value;
	var last = document.getElementById("last").value;
	
	console.log(name+"  "+last);
}

function showAlert(){
	alert("you clicked me!!!");
}


function deleteFunction(){
	alert("Are you sure you want to delete?")
}