package com.example.ktgk_2415053122205_phamquocdat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ktgk_2415053122205_phamquocdat.R

class ChinhSuaChiTieuActivity : AppCompatActivity() {

    private lateinit var edtSuaTen_205: EditText
    private lateinit var edtSuaSoTien_205: EditText
    private lateinit var edtSuaGhiChu_205: EditText
    private lateinit var chkSuaTrangThai_205: CheckBox
    private lateinit var btnLuu_205: Button
    private lateinit var btnHuy_205: Button

    private var khoanChi_205: KhoanChi_205? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_chi_tieu)

        supportActionBar?.title = "Phạm Quốc Đạt - 2415053122205 - Chỉnh sửa khoản chi"

        edtSuaTen_205 = findViewById(R.id.edtSuaTen_205)
        edtSuaSoTien_205 = findViewById(R.id.edtSuaSoTien_205)
        edtSuaGhiChu_205 = findViewById(R.id.edtSuaGhiChu_205)
        chkSuaTrangThai_205 = findViewById(R.id.chkSuaTrangThai_205)
        btnLuu_205 = findViewById(R.id.btnLuu_205)
        btnHuy_205 = findViewById(R.id.btnHuy_205)

        khoanChi_205 = intent.getSerializableExtra("obj_sua") as? KhoanChi_205
        khoanChi_205?.let {
            edtSuaTen_205.setText(it.tenKhoanChi_205)
            edtSuaSoTien_205.setText(it.soTien_205.toString())
            edtSuaGhiChu_205.setText(it.ghiChu_205)
            chkSuaTrangThai_205.isChecked = it.daThanhToan_205
        }

        btnLuu_205.setOnClickListener {
            val tenMoi_205 = edtSuaTen_205.text.toString().trim()
            val soTienStrMoi_205 = edtSuaSoTien_205.text.toString().trim()
            val ghiChuMoi_205 = edtSuaGhiChu_205.text.toString().trim()
            val trangThaiMoi_205 = chkSuaTrangThai_205.isChecked

            if (tenMoi_205.isEmpty()) {
                Toast.makeText(this, "Tên không được để trống!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val soTienMoi_205 = soTienStrMoi_205.toDoubleOrNull()
            if (soTienMoi_205 == null || soTienMoi_205 <= 0) {
                Toast.makeText(this, "Số tiền không hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            khoanChi_205?.apply {
                tenKhoanChi_205 = tenMoi_205
                soTien_205 = soTienMoi_205
                ghiChu_205 = ghiChuMoi_205
                daThanhToan_205 = trangThaiMoi_205
            }

            val intentKetQua_205 = Intent()
            intentKetQua_205.putExtra("khoan_chi_da_sua", khoanChi_205)
            setResult(RESULT_OK, intentKetQua_205)
            finish()
        }

        btnHuy_205.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}