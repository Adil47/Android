<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Student;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\Storage;

class StudentController extends Controller
{
    //
	public function getStudents(Request $req)
	{

		if($req->str)
		{
			$students=Student::
			where("Name","like","%".$req->str."%")
			->orWhere("Phone","like","%".$req->str."%")
			->orWhere("Email","like","%".$req->str."%")

			->get();
			return $students;
		}
		else
		{
			
			$students=Student::get();
			return $students;
		}
		

	}

	public function fetchEmails()
	{
		return Student::select('Email')->where("Token","!=","")->get();
	}




	public function insert(Request $req)
	{

 $validator = Validator::make($req->all(), [
          'Name' => 'required',
	        'Phone' => 'required',
	        'Email'=>'required'
        ]);
		 if($validator->fails())
		 {
		 	return response()->json(["Response"=>"validation failed"]);
		 }

		 $isExist=Student::where(["Email"=>$req->Email])->first();
		 if($isExist)
		 {
		 	return response()->json(["Response"=>"Email Already Exist"]);
		 }

		$std=new Student;
		$std->Name=$req->Name;
		$std->Phone=$req->Phone;
		$std->Email=$req->Email;
		$std->Token=$req->Token;


		//////////////////
	
 		$image = base64_decode($req->Img);
        $safeName = date('mdYHis') . uniqid().'.'.'png';
        $IsUpload=Storage::disk('public')->put('Images/Students/'.$safeName, $image);
        //////////////
       if($IsUpload)
		$std->Image='Images/Students/'.$safeName;
       
		if($std->save())
		{
			return response()->json(["Response"=>"Inserted"]);
		}
		else
		{
			return response()->json(["Response"=>"Error"]);
		}

	}
public function update(Request $req)
	{

 $validator = Validator::make($req->all(), [
          'Name' => 'required',
	        'Phone' => 'required',
	        'Email'=>'required',
	        'StudentId'=>'required'

        ]);
		 if($validator->fails())
		 {
		 	return response()->json(["Response"=>"validation failed"]);
		 }

		$std=Student::where(["Id"=>$req->StudentId])->first();

		if(!$std)
			return response()->json(["Response"=>"Not Found"]);

		//////////////////
		$isUpdated=0;
		if($req->Img!="")
		{
		$image = base64_decode($req->Img);
        $safeName = date('mdYHis') . uniqid().'.'.'png';
        $IsUpload=Storage::disk('public')->put('Images/Students/'.$safeName, $image);
		 if($IsUpload)
		 {
		 	
		 	Storage::delete('public/'.$std->Image);
		 	$isUpdated=Student::where(["Id"=>$req->StudentId])
		 	->update([
		 		'Image' => 'Images/Students/'.$safeName,
		 		'Name' => $req->Name,
	        	'Phone' => $req->Phone,
	        	'Email'=>$req->Email
		 	]);
		 }
		
		}
 		else
 		{
 			$isUpdated=Student::where(["Id"=>$req->StudentId])->update(
			['Name' => $req->Name,
	        'Phone' => $req->Phone,
	        'Email'=>$req->Email]
			);

 		}
        //////////////
      
		
		if($isUpdated)
		{
			return response()->json(["Response"=>"updated"]);
		}
		else
		{
			return response()->json(["Response"=>"Error"]);
		}

	}
public function delete(Request $req)
	{

 			$validator = Validator::make($req->all(), [
          		'StudentId' => 'required'
      			]);
		 if($validator->fails())
		 {
		 	return response()->json(["Response"=>"validation failed"]);
		 }

		 if(!Student::where(["Id"=>$req->StudentId])->first())
		{
		 	return response()->json(["Response"=>"Not Found"]);
		 }

		if(Student::where(["Id"=>$req->StudentId])->delete())
		{
			return response()->json(["Response"=>"deleted"]);
		}
		else
		{
			return response()->json(["Response"=>"Error"]);
		}

	}

	public function sendNotification(Request $req)
	{
		$Student =Student::where('Email',"=",$req->Email)->first();
		$token= $Student->Token;
		$title=$req->Title;
		$msg=$req->Message;
		$res=$this->push_notification_android($token,$msg,$title);
		return $res;
	}

	function push_notification_android($device_id,$message,$title){

    //API URL of FCM
    $url = 'https://fcm.googleapis.com/fcm/send';

    /*api_key available in:
    Firebase Console -> Project Settings -> CLOUD MESSAGING -> Server key*/    
    $api_key = 'AAAAaerj6Zg:APA91bHojlJg9MtNcD9yfVZnDF85om2E6B8u_dTJMBDLUzkyIHD34602o3yeJLLvkRPTsn-sD5pDUQ05uH_5Sm28ASDKRApwRIl8KTyakX-Qze9V-6tWB86jqJbh8FcrLX8DRztL7ujQ';
                
    $fields = array (
        'registration_ids' => array (
                $device_id
        ),
        'data' => array (
                "message" => $message
        ),
        'notification'=>array(
        	"title"=>$title,
        	"body"=>$message,
        	"sound"=>"default"

        )

    );

    //header includes Content type and api key
    $headers = array(
        'Content-Type:application/json',
        'Authorization:key='.$api_key
    );
                
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
    $result = curl_exec($ch);
    if ($result === FALSE) {
        die('FCM Send Error: ' . curl_error($ch));
    }
    curl_close($ch);
    return $result;
}


}
