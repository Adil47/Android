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

		$std=new Student;
		$std->Name=$req->Name;
		$std->Phone=$req->Phone;
		$std->Email=$req->Email;

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
}
