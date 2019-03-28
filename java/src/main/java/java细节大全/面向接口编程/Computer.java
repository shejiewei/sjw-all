package java细节大全.面向接口编程;

/**
 * Created by shejiewei on 2019/3/28.
 */
public class Computer {

    private  IMobileStorage _usbDrive;

    public IMobileStorage get_flashDisk() {
        return _usbDrive;
    }

    public void set_flashDisk(IMobileStorage _usbDrive) {
        this._usbDrive = _usbDrive;
    }
    public  Computer(){

    }
    public Computer(IMobileStorage _usbDrive)

    {
        this._usbDrive=_usbDrive;
    }

    public void ReadData(){
        this._usbDrive.Read();
    }

    public void WriteData(){
        this._usbDrive.Write();
    }


}
