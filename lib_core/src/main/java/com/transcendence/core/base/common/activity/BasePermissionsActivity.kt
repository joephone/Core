package com.transcendence.core.base.common.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.permissionx.guolindev.PermissionMediator
import com.permissionx.guolindev.PermissionX
import com.transcendence.core.R

/**
 * @author joephone
 * @date 2023/6/13
 * @desc
 */
abstract class BasePermissionsActivity : AppAc() {

    private val permissionX: PermissionMediator by lazy { PermissionX.init(this) }

    open fun getActivityResultLauncher(resultCallback: (resultCode: Int, data: Intent?) -> Unit): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            resultCallback.invoke(result.resultCode, result.data)
        }
    }

    /**
     * 拨打电话
     */
    open fun callPhone(phone: String) {
        permissionX.permissions(Manifest.permission.CALL_PHONE)
            .explainReasonBeforeRequest()
            .onExplainRequestReason { scope, deniedList, beforeRequest ->
                scope.showRequestReasonDialog(deniedList, getString(R.string.permission_request_content_dial), "我已知晓", "取消")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "请在设置中允许以下权限", "立即前往", "取消")
            }
            .request { allGranted, _, _ ->
                if (allGranted) {
                    //申请的权限全部允许
                    val intent = Intent(Intent.ACTION_CALL)
                    val data = Uri.parse("tel:${phone}")
                    intent.data = data
                    startActivity(intent)
                }
            }
    }

    /**View截图保存相册*/
//    fun saveImg(view: View) {
//        permissionX.permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            .onForwardToSettings { scope, deniedList ->
//                scope.showForwardToSettingsDialog(deniedList, "请在设置中允许以下权限", "立即前往", "取消")
//            }
//            .request { allGranted, _, _ ->
//                if (allGranted) {
//                    if (view.saveImage()) {
//                        showToast("已保存至系统相册")
//                    }
//                } else {
//                    showToast("无文件读写权限，保存图片失败")
//                }
//            }
//    }

    /**
     * 申请读写权限
     */
    fun applyReadWritePermissions(function: (Boolean) -> Unit) {
        permissionX.permissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
            .explainReasonBeforeRequest()
            .onExplainRequestReason { scope, deniedList, beforeRequest ->
                scope.showRequestReasonDialog(deniedList, "即将申请以下权限，请您同意", "我已知晓", "取消")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "请在设置中允许以下权限", "立即前往", "取消")
            }
            .request { allGranted, _, _ ->
                function.invoke(allGranted)
            }
    }

    /**申请安装权限**/
    fun applyInstallPermissions(function: (Boolean) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionX.permissions(Manifest.permission.REQUEST_INSTALL_PACKAGES)
                .explainReasonBeforeRequest()
                .onExplainRequestReason { scope, deniedList, beforeRequest ->
                    scope.showRequestReasonDialog(deniedList, getString(R.string.permission_request_content_install), "前往授权", "暂不授予")
                }
                .onForwardToSettings { scope, deniedList ->
                    scope.showForwardToSettingsDialog(deniedList, "请在设置中允许以下权限", "立即前往", "暂不授予")
                }
                .request { allGranted, _, _ ->
                    function.invoke(allGranted)
                }
            return
        }
        function.invoke(true)
    }

    /**
     * 申请定位权限
     */
    fun applyLocationPermissions(function: (Boolean) -> Unit) {
        permissionX.permissions(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
            .explainReasonBeforeRequest()
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, getString(R.string.permission_request_content_location), "前往授权", "暂不授予")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "请在设置中允许以下权限", "立即前往", "暂不授予")
            }
            .request { allGranted, _, _ ->
                function.invoke(allGranted)
            }
    }

    /**
     * 开启相机或外部存储
     */
    fun applyCameraOrExternalStorage(function: (Boolean) -> Unit) {
        permissionX.permissions(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
        )
            .explainReasonBeforeRequest()
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, getString(R.string.permission_request_content_camera), "我已知晓", "取消")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "请在设置中允许以下权限", "立即前往", "取消")
            }
            .request { allGranted, _, _ ->
                if (allGranted) {
                    function.invoke(allGranted)
                }
            }
    }
}