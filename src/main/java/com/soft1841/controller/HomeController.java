package com.soft1841.controller;

import com.soft1841.bean.StudentMsg;
import com.soft1841.data.STUDENT_DATA;
import com.soft1841.factory.ConText;
import com.soft1841.model.AppModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController extends RootController implements Initializable {

    public BorderPane homePage;
    public BorderPane ex_page;
    public ComboBox<String> doorComboBox;
    public ComboBox<String> sexComboBox;
    public Button homeButton;
    public TextField searchText;
    public Button serachButton;
    public ListView msgList;

    private AppModel appModel = new AppModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STUDENT_DATA.setMsg();
        doorComboBox.getItems().addAll("112", "113", "114","115" ,"116","346");
        sexComboBox.getItems().addAll("男生", "女生");
//初始化列表信息
        changelist(STUDENT_DATA.StudentMsgarray);
    }

    //清除textfield搜索框
    public void clearText() {
        searchText.setText("");
    }

    //清除list
    public void clearstudentList() {
        ObservableList<StudentMsg> list = msgList.getItems();
        msgList.getItems().removeAll();
    }

    //listchange
    public void changelist(ArrayList<StudentMsg> stulist) {
        ArrayList<StudentMsg> studentMsgsListByOrder = (ArrayList<StudentMsg>) appModel.nameSort(stulist);

        ArrayList<Parent> parentList = new ArrayList<>();
        for (StudentMsg msg : studentMsgsListByOrder) {
            try {
                Parent card = FXMLLoader.load(getClass().getResource("/fxml/listViewCard.fxml"));
                CardController cardController = (CardController) ConText.controllerHashMap.get(CardController.class.getSimpleName());
                cardController.createCard(msg);
                parentList.add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ObservableList<Parent> list2 = FXCollections.observableArrayList(parentList);
        msgList.setItems(list2);

    }

    //查询事件（通过keyword）
    public void searchByKey() {
        String keyWord4search = searchText.getText();
        if (!keyWord4search.equals("") && keyWord4search != null) {
            clearstudentList();
            ArrayList<StudentMsg> arrayListByKey = appModel.searchStudent(STUDENT_DATA.StudentMsgarray, keyWord4search);

            changelist(arrayListByKey);
        } else {
            //TODO messgae nothing
        }
    }

    //通过性别筛选
    public void sortBySex() {
        String selectSex = sexComboBox.getValue();
        ArrayList<StudentMsg> arrayListBySex = appModel.sexSort(STUDENT_DATA.StudentMsgarray, selectSex);
        clearstudentList();
        changelist(arrayListBySex);
    }

    //通过宿舍筛选
    public void sortBydormitory() {
        String selectDormitory = doorComboBox.getValue();
        ArrayList<StudentMsg> arrayListByDoor = appModel.dormitorySort(STUDENT_DATA.StudentMsgarray, selectDormitory);
        clearstudentList();
        changelist(arrayListByDoor);
    }
 //返回首页
    public void backStar() {
        clearstudentList();
        doorComboBox.setValue("宿舍号");
        sexComboBox.setValue("性别");
        changelist(STUDENT_DATA.StudentMsgarray);

    }


}
