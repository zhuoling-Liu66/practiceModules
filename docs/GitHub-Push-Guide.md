# GitHub 提交流程

本文档记录这个项目从本地提交到 GitHub 的标准流程，也解释这次遇到的两个常见问题。

## 一、这次为什么会失败

### 1. `remote origin already exists`

这表示远程仓库别名 `origin` 已经配置过了，所以不能重复执行：

```powershell
git remote add origin https://github.com/zhuoling-Liu66/practiceModules.git
```

先检查：

```powershell
git remote -v
```

如果地址不对，再修改：

```powershell
git remote set-url origin https://github.com/zhuoling-Liu66/practiceModules.git
```

### 2. 远程仓库已经有一次初始化提交

GitHub 页面里已经有一个 `Initial commit`，说明远程 `main` 不是完全空的。

这时本地直接推送，常见会遇到“本地和远程分叉”的问题。正确做法是先拉取远程，再把本地提交整合进去。

### 3. `Failed to connect to github.com port 443`

这个报错通常是网络连接问题，常见原因有：

- 当时网络波动
- 代理或 VPN 配置异常
- 公司 / 学校网络限制
- GitHub 一时不可达

这类错误和 Git 命令本身通常没关系，过一会儿重试或者切换网络往往就能恢复。

## 二、标准提交流程

### 1. 先查看仓库状态

```powershell
git status
git branch -vv
git remote -v
```

作用：

- `git status` 看有没有未提交文件
- `git branch -vv` 看当前分支
- `git remote -v` 看远程地址是否正确

### 2. 把文件加入暂存区

```powershell
git add .
```

如果想更稳妥，也可以只加指定文件：

```powershell
git add README.md
git add docs/GitHub-Push-Guide.md
git add src
```

### 3. 创建本地提交

```powershell
git commit -m "docs: add GitHub push guide and refine README"
```

### 4. 拉取远程分支

第一次推送前，建议先执行：

```powershell
git fetch origin
git rebase origin/main
```

如果你不熟悉 `rebase`，也可以先用：

```powershell
git pull origin main --allow-unrelated-histories
```

这个项目里，本地和远程最开始是分别初始化的，所以历史不是同一条线，必要时需要 `--allow-unrelated-histories`。

### 5. 解决冲突（如果出现）

如果 Git 提示冲突：

1. 打开冲突文件
2. 手动保留想要的内容
3. 删除冲突标记

冲突标记长这样：

```text
<<<<<<< HEAD
本地内容
=======
远程内容
>>>>>>> origin/main
```

处理完后继续：

```powershell
git add .
git rebase --continue
```

如果你用的是 `pull` 合并方式，则继续：

```powershell
git add .
git commit
```

### 6. 推送到 GitHub

```powershell
git push -u origin main
```

第一次成功后，以后可以直接用：

```powershell
git push
```

## 三、这次项目建议使用的命令顺序

```powershell
git status
git remote -v
git add .
git commit -m "docs: add GitHub push guide and refine README"
git fetch origin
git rebase origin/main
git push -u origin main
```

## 四、常用排查命令

查看远程地址：

```powershell
git remote -v
```

查看提交历史：

```powershell
git log --oneline --graph --decorate --all
```

测试是否能访问远程仓库：

```powershell
git ls-remote origin
```

查看本地和远程谁领先：

```powershell
git log --oneline --left-right HEAD...origin/main
```

## 五、给你的一个简单理解

可以把 Git 提交理解成 3 步：

1. `git add`：把“准备提交的文件”放到待提交区
2. `git commit`：在本地生成一次正式记录
3. `git push`：把本地记录上传到 GitHub

也就是说：

- `commit` 只是保存在你电脑里
- `push` 才是发到 GitHub 上

## 六、以后最常用的一套命令

日常开发最常用的其实就是这几条：

```powershell
git status
git add .
git commit -m "your message"
git push
```

如果推送失败，再补查：

```powershell
git fetch origin
git log --oneline --left-right HEAD...origin/main
```
